package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.huangfugui.ibatis.po.Picture;
import org.springframework.stereotype.Repository;

/**
 * Created by zqb on 2017/7/7.
 */
@Repository
public interface PictureMapper {

    /**
    * @name
    * @description
    * @param picture
    * @return
    */
    int insertPic(Picture picture);


    Picture selectById(@Param("picId") int picId);
}
