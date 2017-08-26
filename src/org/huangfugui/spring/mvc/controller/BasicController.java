package org.huangfugui.spring.mvc.controller;

import org.huangfugui.dto.Result;
import org.huangfugui.spring.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Controller
@RequestMapping("/basic")
public class BasicController {

    @Autowired
    private BasicService basicService;

    @RequestMapping(value = "/register",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result registerHandler(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        return basicService.registerUser(username,password,userType);
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result loginHandler(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        return basicService.login(username, password, userType, request);
    }

    @RequestMapping(value = "/updateNickname",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateNicknameHandler(HttpServletRequest request, Model model){
        String nickname = request.getParameter("nickname");
        return basicService.updateNickname(nickname,request);
    }

    @RequestMapping(value = "/updateSign",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateSignHandler(HttpServletRequest request, Model model){
        String sign = request.getParameter("sign");
        return basicService.updateSign(sign,request);
    }

    @RequestMapping(value = "/updateSex",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateSexHandler(HttpServletRequest request, Model model){
        String sexString = request.getParameter("sexString");
        return basicService.updateSex(sexString,request);
    }

    @RequestMapping(value = "/updatePassword",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updatePasswordHandler(HttpServletRequest request, Model model){
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        return basicService.updatePassword(oldPassword,newPassword,request);
    }

    @RequestMapping(value = "/upload",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result uploadHandler(HttpServletRequest request, Model model){
        return basicService.upload(request);
    }

    @RequestMapping(value = "/allPicWaitForTag",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result allPicWaitForTag(HttpServletRequest request, Model model){
        return basicService.allPicWaitForTag(request);
    }

    @RequestMapping(value = "/doTag",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result doTag(HttpServletRequest request,Model model){
        String picId = request.getParameter("picId");
        String labelIdList = request.getParameter("labelIdList");
        String labelInput = request.getParameter("labelInput");
        String inputFlag = request.getParameter("inputFlag");
        if(inputFlag.equals("0")){// 用户只提交了待选定的标签
            return basicService.doTagNoInput(request, picId, labelIdList);
        }
        else{// 用户自定义输入
            return basicService.doTagOnlyInput(request, picId, labelInput);
        }
    }
    @RequestMapping(value = "/showTagOfPic",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result showTagOfPic(HttpServletRequest request,Model model){
        String picId = request.getParameter("picId");
        return basicService.showTagOfPic(request,picId);
    }

}