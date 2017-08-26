import org.huangfugui.ibatis.mapper.LabelInputMapper;
import org.huangfugui.ibatis.po.LabelInput;
import org.huangfugui.spring.MyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by huangfugui on 2017/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class LabelInputMapperTest {

    @Autowired
    private LabelInputMapper labelInputMapper;

    @Test
    public void insertLabelInput() throws Exception {
        LabelInput labelInput = new LabelInput();
        labelInput.setInput("haha");
        labelInput.setUserId(1);
        labelInput.setPicId(1);
        labelInputMapper.insertLabelInput(labelInput);
        System.out.println(labelInput);
    }

}