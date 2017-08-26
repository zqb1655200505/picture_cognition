package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.huangfugui.ibatis.po.LabelInput;
import org.springframework.stereotype.Repository;

/**
 * Created by huangfugui on 2017/7/31.
 */
@Repository
public interface LabelInputMapper {
    /**
     * insert用户自定义输入
     * @param labelInput
     * @return
     */
    int insertLabelInput(LabelInput labelInput);
}
