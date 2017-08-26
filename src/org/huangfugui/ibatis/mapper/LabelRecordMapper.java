package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by huangfugui on 2017/7/22.
 */
@Repository
public interface LabelRecordMapper {
    /**
     * 用户打标签后记录用户记录
     * 暂时有无自定义输入先分开，打第一轮
     * @param userId
     * @param picId
     * @param labelId
     * @param count
     * @return
     */
    int insertRecord(@Param("userId") int userId,
                     @Param("picId") int picId,
                     @Param("labelId") int labelId,
                     @Param("count") int count);
}

