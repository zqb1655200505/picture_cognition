package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.huangfugui.ibatis.po.Label;
import org.springframework.stereotype.Repository;

/**
 * Created by zqb on 2017/7/7.
 */
@Repository
public interface LabelMapper {
    /**
     * @name insertNormal
     * @description 插入图片识别算法得到的标签或自定义输入非名词
     * @param  label 标签名
     * @return 影响行数
     */
    int insertNormal(Label label);

    /**
     * 插入自定义输入名词
     * @param label
     * @return
     */
    int insertWithNoun(Label label);

    Label selectById(@Param("labelId") int labelId);
}
