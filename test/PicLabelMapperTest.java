import org.huangfugui.ibatis.mapper.LabelMapper;
import org.huangfugui.ibatis.mapper.PicLabelMapper;
import org.huangfugui.ibatis.mapper.PictureMapper;
import org.huangfugui.spring.MyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zqb on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class PicLabelMapperTest {

    @Autowired
    private PicLabelMapper picLabelMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Test
    public void selectById() throws Exception {
        System.out.println(picLabelMapper.selectById(1));
    }

    @Test
    public void selectById1() throws Exception {
        System.out.println(pictureMapper.selectById(1));
    }

    @Test
    public void selectById2() throws Exception {
        System.out.println(labelMapper.selectById(1));
    }

    @Test
    public void getLabelByPicId() throws Exception {
        System.out.println(picLabelMapper.getLabelByPicId(1,0));
    }
}