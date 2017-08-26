package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.huangfugui.ibatis.po.PicLabel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zqb on 2017/7/7.
 */
@Repository
public interface PicLabelMapper {

    /**
    * @name
    * @description
    * @param picLabel
    * @return
    */
    int insertPicLabel(PicLabel picLabel);

    /**
    * @name getLabelByPicId
    * @description
    * @param  picId 查询某一图片某轮次所有标签
    * @param  mCount
    * @return
    */
    List<PicLabel> getLabelByPicId(@Param("picId") int picId, @Param("mCount") int mCount);

    /**
     * 根据打标签轮数查找全部图片标签情况
     * @param mCount
     * @return
     */
    List<PicLabel> getAllLabelPic(@Param("mCount") int mCount);

    PicLabel selectById(@Param("picLabelId") int picLabelId);

    /**
     * 当用户从给定标签选定并提交后，进行图片相应标签权值相加
     * @param picId
     * @param addScore
     * @return
     */
    int updateForTagNoInput(@Param("picId") int picId, @Param("labelId") int labelId, @Param("addScore") float addScore);

}