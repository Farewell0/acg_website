package com.wzz.acg.dao;

import com.wzz.acg.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectByUsername(String username);

    int selectByEmail(String email);

    int selectByPhone(String phone);

    User selectByUsernamePwd(@Param("username") String username, @Param("password") String password);

    int selectByPassword(@Param("userId") Integer userId, @Param("oldPassword") String oldPassword);

    String selectQuestionByUsername(String username);

    int selectByUsernameAndQA(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePwdByUsername(@Param("username") String username, @Param("newPassword") String newPassword);

    List<User> selectAllUser();
}