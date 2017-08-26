package org.huangfugui.ibatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.huangfugui.ibatis.enums.Sex;
import org.huangfugui.ibatis.enums.UserType;
import org.huangfugui.ibatis.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangfugui on 2017/4/27.
 */
@Repository
public interface UserMapper {

    /**
     * 用户注册
     * @param username
     * @param password
     * @param userType
     * @return
     */
    int insertUser(@Param("username") String username, @Param("password") String password, @Param("userType") UserType userType);

    /**
     * 用户登录，权限校验
     * @param username
     * @param password
     * @param userType
     * @return
     */
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password, @Param("userType") UserType userType);

    /**
     * 更新用户昵称
     * @param id
     * @param nickname
     * @return
     */
    int updateNicknameById(@Param("id") int id,@Param("nickname") String nickname);

    /**
     * 更新用户签名
     * @param id
     * @param sign
     * @return
     */
    int updateSignById(@Param("id") int id,@Param("sign") String sign);

    /**
     * 更改用户性别
     * @param id
     * @param sex
     * @return
     */
    int updateSexById(@Param("id") int id, @Param("sex") Sex sex);

    /**
     * 在更新密码前必须能够输入正确的旧密码
     * @param id
     * @param oldPassword
     * @return
     */
    User selectUserBeforeUpdatePassword(@Param("id") int id,@Param("oldPassword") String oldPassword);

    /**
     * 更新用户密码
     * @param id
     * @param newPassword
     * @return
     */
    int updatePasswordById(@Param("id") int id,@Param("newPassword") String newPassword);
}
