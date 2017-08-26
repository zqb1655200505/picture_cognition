import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.huangfugui.ibatis.util.TransApi;
import org.huangfugui.spring.MyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Created by zqb on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class TransApiTest {

    @Test
    public void test() throws Exception {
        BufferedReader bufferedReader=null;
        String s=null;//basePath+fileName.substring(0,fileName.lastIndexOf("."))+".txt")
        FileInputStream fileInputStream=new FileInputStream(new File("F:\\cat.txt"));
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
        }
//        String res=TransApi.getTransResult("hello","en","zh");
//        System.out.println(res);




    }

}