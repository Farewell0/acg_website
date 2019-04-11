package com.wzz.acg.service;

import com.wzz.acg.common.ServerResponse;
import com.wzz.acg.pojo.User;

import java.util.List;

public interface UserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String arg, String type);

    ServerResponse<String> resetPassword(User user, String oldPassword, String newPassword);

    ServerResponse<User> updateUserInfo(User user, String gender, String email, String phone, String address);

    ServerResponse<String> getQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgetResetPwd(String username, String newPassword, String token);

    ServerResponse<List<User>> getAllUser();
}
