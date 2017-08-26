package org.huangfugui.spring.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.huangfugui.dto.Result;
import org.huangfugui.ibatis.enums.Sex;
import org.huangfugui.ibatis.enums.UserType;
import org.huangfugui.ibatis.mapper.*;
import org.huangfugui.ibatis.po.*;
import org.huangfugui.ibatis.util.TransApi;
import org.huangfugui.spring.service.tools.ToPicLabelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Component
public class BasicService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private PictureMapper pictureMapper;


    @Autowired
    private PicLabelMapper picLabelMapper;

    @Autowired
    private LabelRecordMapper labelRecordMapper;

    @Autowired
    private LabelInputMapper labelInputMapper;

    public Result registerUser(String username, String password,String userType){
        UserType userType1;
        if(userType.equals("0")){
            userType1 = UserType.USER;
        }
        else{
            userType1 = UserType.ADMINISTRATOR;
        }
        int result = userMapper.insertUser(username,password,userType1);
        if(result==1){
            //注册成功
            return new Result(1,"注册成功");
        }
        //已有用户名，注册失败
        return new Result(0,"用户名已存在，注册失败");
    }

    public Result login(String username, String password, String userType, HttpServletRequest request){
        UserType userType1;
        if(userType.equals("0")){
            userType1 = UserType.USER;
        }
        else{
            userType1 = UserType.ADMINISTRATOR;
        }
        User user = userMapper.selectByUsernameAndPassword(username,password,userType1);
        if(user!=null){
            //登录记录session
            HttpSession session = request.getSession();
            session.setAttribute("userSession",user);
            //向客户端返回user对象数据
            List<User> list = new ArrayList<User>();
            list.add(user);
            return new Result(1,"登陆成功",list);//数据库中有相应用户名与密码
        }
        return new Result(0,"用户名或密码错误");
    }

    public Result updateNickname(String nickname,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        int result = userMapper.updateNicknameById(user.getId(),nickname);
        if(result==1){
            return new Result(1,"更新昵称成功");
        }
        else{
            return new Result(0,"更新昵称失败");
        }
    }

    public Result updateSign(String sign,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        int result = userMapper.updateSignById(user.getId(),sign);
        if(result==1){
            return new Result(1,"更新签名成功");
        }
        else{
            return new Result(0,"更新签名失败");
        }
    }

    public Result updateSex(String sexString,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        Sex sex = null;
        if(sexString.equals("0")){
            sex = Sex.MALE;
        }
        else{
            sex = Sex.FEMALE;
        }
        int result = userMapper.updateSexById(user.getId(),sex);
        if(result==1){
            return new Result(1,"更新性别成功");
        }
        else{
            return new Result(0,"更新性别失败");
        }
    }

    public Result updatePassword(String oldPassword,String newPassword,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        User checkUser = userMapper.selectUserBeforeUpdatePassword(user.getId(),oldPassword);
        if(checkUser!=null){
            int result = userMapper.updatePasswordById(user.getId(),newPassword);
            if(result==1){
                return new Result(1,"更新密码成功");
            }
        }
        return new Result(0,"更新密码失败");
    }

    public Result upload(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println(multipartRequest.getParameter("uploadText"));
        List<MultipartFile> multipartFileList = multipartRequest.getFiles("uploadFiles");

        String basePath="/mnt/sdc/static_picture_cognition/";
        String savePath = basePath+user.getId();
        //String savePath = session.getServletContext().getRealPath("/") + "pic/"+user.getId();
        File directory = new File(savePath);
        if(!directory.exists()){
            directory.mkdirs();
        }
        try {
            BufferedReader bufferedReader=null;
            String s=null;
            for(MultipartFile multipartFile:multipartFileList){
                String fileName = multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(savePath+"/"+fileName));

                Picture picture=new Picture();
                picture.setPath(savePath+"/"+fileName);
                picture.setmCount(0);
                pictureMapper.insertPic(picture);
                //此处调用python脚本生成图片原始标签
                //脚本位置 /root/glog-0.3.3/caffe/python py-classify.py
                //文件位置 savePath+"/"+fileName

                String[] args = new String[3];
                // 本地测试能用
                args[0]="python";//表示要执行Python脚本
                //args[1]="/usr/tomcat/a.py";
                args[1]="/root/glog-0.3.3/caffe/python/py-classify.py";//此为要执行的脚本全路径
                args[2]=savePath+"/"+fileName;//此为python脚本需要的参数（被处理文件全路径），若不止一个则继续添加到数组下一个位置
                Process pr = Runtime.getRuntime().exec(args);
                bufferedReader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                while((s=bufferedReader.readLine()) != null)//以防python脚本有输出而阻塞线程
                {
                    System.out.println(s);
                }
                pr.waitFor();


                //读取图片标签文件
                //basePath+fileName.substring(0,fileName.lastIndexOf("."))+".txt"
                FileInputStream fileInputStream=new FileInputStream(new File(basePath+fileName.substring(0,fileName.lastIndexOf("."))+".txt"));
                bufferedReader=new BufferedReader(new InputStreamReader(fileInputStream));
                while((s=bufferedReader.readLine()) != null)//每次读取一行数据，即一个标签
                {
                    String labels=s.split(",")[0].substring(14);
                    //翻译标签
                    String res= TransApi.getTransResult(labels,"en","zh");//翻译后的中文标签
                    JSONObject jsonObject=JSONObject.fromObject(res);
                    JSONArray jsonArray=JSONArray.fromObject(jsonObject.getString("trans_result"));
                    JSONObject object=(JSONObject) jsonArray.get(0);
                    String label=object.getString("dst");
                    System.out.println(label);

                    //入库
                    Label mLabel=new Label();
                    mLabel.setLabelName(label);
                    labelMapper.insertNormal(mLabel);

                    PicLabel picLabel=new PicLabel();
                    picLabel.setLabel(mLabel);
                    picLabel.setPicture(picture);
                    picLabel.setmCount(0);
                    picLabel.setScore(0);
                    picLabelMapper.insertPicLabel(picLabel);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0,"上传失败");
        }
        return new Result(1,"上传成功");
    }

    public Result allPicWaitForTag(HttpServletRequest request){
        //最开始图片打标签轮数为0
        List<PicLabel> picLabels = picLabelMapper.getAllLabelPic(0);

        List<PicLabelData> labelDataList = ToPicLabelData.transfer2PicLabelData(picLabels);

        return new Result(1,"请求成功",labelDataList);
    }

    public Result doTagNoInput(HttpServletRequest request, String picId, String labelIdList){
        String s = labelIdList.substring(1, labelIdList.length()-1);
        String[] strings = s.split(", ");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        //更新图片权值
        for(String s1:strings){
            picLabelMapper.updateForTagNoInput(Integer.valueOf(picId),Integer.valueOf(s1),user.getConfidence());
            labelRecordMapper.insertRecord(user.getId(),Integer.valueOf(picId),Integer.valueOf(s1),0);
        }
        //用户打标签log
        return new Result(1,"打标签成功");
    }

    public Result doTagOnlyInput(HttpServletRequest request, String picId, String labelInput){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("userSession");
        // 将用户自定义输入入库
        LabelInput labelInput1 = new LabelInput(user.getId(), Integer.valueOf(picId), labelInput);
        labelInputMapper.insertLabelInput(labelInput1);
        int fatherId = labelInput1.getId();//回填字段

        List<String> strings = new ArrayList<String>();
        org.ansj.domain.Result result = ToAnalysis.parse(labelInput);
        String[] oriTags = result.toString().split(",");

        for (String tag : oriTags){
            // 分词标签入库 && 打标签
            String[] tag_split =  tag.split("/");
            Label label = new Label();
            label.setLabelName(tag_split[0]);
            if(tag_split[1].equals("nr")||tag_split[1].equals("n")){// 是名词
                label.setFatherId(fatherId);
                label.setIsNoun(1);
                labelMapper.insertWithNoun(label);
            } else{// 名词外的其他词性
                labelMapper.insertNormal(label);
            }
            PicLabel picLabel = new PicLabel();
            Picture picture = new Picture();
            picture.setId(Integer.valueOf(picId));
            picLabel.setPicture(picture);
            picLabel.setmCount(0);
            picLabel.setLabel(label);
            picLabel.setScore(user.getConfidence());
            picLabelMapper.insertPicLabel(picLabel);

            // 记录用户图片打标签历史
            labelRecordMapper.insertRecord(user.getId(), Integer.valueOf(picId), label.getId(), 0);
        }
        strings.add(result.toString());
        return new Result(1,"分词成功",strings);
    }

    public Result showTagOfPic(HttpServletRequest request,String picId){
        List<PicLabel> picLabels = picLabelMapper.getLabelByPicId(Integer.valueOf(picId),0);
        List<PicLabelData> labelDataList = ToPicLabelData.transfer2PicLabelData(picLabels);
        return new Result(1,"请求成功",labelDataList);
    }
}