import org.huangfugui.ibatis.mapper.LabelMapper;
import org.huangfugui.ibatis.po.Label;
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
public class LabelMapperTest {
    @Autowired
    private LabelMapper labelMapper;

    @Test
    public void insertWithNoun() throws Exception {
        Label label = new Label();
        label.setLabelName("测试");
        label.setFatherId(100);
        label.setIsNoun(1);
        labelMapper.insertWithNoun(label);
        System.out.println(label);
    }

}