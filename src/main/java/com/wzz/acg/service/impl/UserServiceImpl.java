package com.wzz.acg.service.impl;

import com.wzz.acg.common.Const;
import com.wzz.acg.common.ServerResponse;
import com.wzz.acg.common.TokenCache;
import com.wzz.acg.dao.UserMapper;
import com.wzz.acg.pojo.User;
import com.wzz.acg.service.UserService;
import com.wzz.acg.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int rowCount = userMapper.selectByUsername(username);
        if(rowCount <= 0){
            return ServerResponse.createByErrorMessage("用户名不存在！");
        }
        User user = userMapper.selectByUsernamePwd(username, MD5Util.MD5EncodeUtf8(password));
        if(user != null){
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("登录成功!", user);
        }
        return ServerResponse.createByErrorMessage("密码错误！");
    }

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse<String> serverResponse = checkValid(user.getUsername(), Const.USERNAME);
        if(!serverResponse.isSuccess())
            return serverResponse;
        serverResponse = checkValid(user.getEmail(), Const.EMAIL);
        if(!serverResponse.isSuccess())
            return serverResponse;
        serverResponse = checkValid(user.getPhone(), Const.PHONE);
        if(!serverResponse.isSuccess())
            return serverResponse;

        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));    // MD5加密
        int rowCount = userMapper.insert(user);
        if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("注册成功!");
        }
        return ServerResponse.createByErrorMessage("注册失败!");
    }

    @Override
    public ServerResponse<String> checkValid(String arg, String type) {
        if(StringUtils.isNotBlank(arg)){
            if(Const.USERNAME.equals(type)){
                int rowCount = userMapper.selectByUsername(arg);
                if(rowCount > 0){
                    return ServerResponse.createByErrorMessage("该用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int rowCount = userMapper.selectByEmail(arg);
                if(rowCount > 0){
                    return ServerResponse.createByErrorMessage("该邮箱已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int rowCount = userMapper.selectByPhone(arg);
                if(rowCount > 0){
                    return ServerResponse.createByErrorMessage("该手机号已存在");
                }
            }
            return ServerResponse.createBySuccessMessage("校验成功");
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
    }

    @Override
    public ServerResponse<String> resetPassword(User user, String oldPassword, String newPassword) {
        int rowCount = userMapper.selectByPassword(user.getId(), MD5Util.MD5EncodeUtf8(oldPassword));
        if(rowCount <= 0){
            return ServerResponse.createByErrorMessage("当前密码输入错误!");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
        rowCount = userMapper.updateByPrimaryKeySelective(user);
        if(rowCount > 0){
            return ServerResponse.createBySuccessMessage("重置用户密码成功！");
        }
        return ServerResponse.createByErrorMessage("重置用户密码失败！");
    }

    @Override
    public ServerResponse<User> updateUserInfo(User user, String gender, String email, String phone, String address) {
        User newUser = new User();
        newUser.setId(user.getId());
        if(StringUtils.isNotEmpty(gender))
            newUser.setGender(gender);
        if(StringUtils.isNotEmpty(email))
            newUser.setEmail(email);
        if(StringUtils.isNotEmpty(phone))
            newUser.setPhone(phone);
        if(StringUtils.isNotEmpty(address))
            newUser.setAddress(address);

        int rowCount = userMapper.updateByPrimaryKeySelective(newUser);
        if(rowCount > 0) {
            User u = userMapper.selectByPrimaryKey(newUser.getId());
            u.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("更新用户信息成功！", u);
        }
        return ServerResponse.createByErrorMessage("更新用户信息失败！");
    }

    @Override
    public ServerResponse<String> getQuestion(String username) {
        ServerResponse serverResponse = this.checkValid(username, Const.USERNAME);
        if(serverResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在！");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)){
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("密保问题为空！");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        ServerResponse serverResponse = this.checkValid(username, Const.USERNAME);
        if(serverResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在！");
        }
        int rowCount = userMapper.selectByUsernameAndQA(username, question, answer);
        if(rowCount > 0){
            String token = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, token);
            return ServerResponse.createBySuccess(token);
        }
        return ServerResponse.createByErrorMessage("密保问题答案错误！");
    }

    @Override
    public ServerResponse<String> forgetResetPwd(String username, String newPassword, String token) {
        if(StringUtils.isBlank(token))
            return ServerResponse.createByErrorMessage("参数错误，token需要传递！");

        ServerResponse serverResponse = this.checkValid(username, Const.USERNAME);
        if(serverResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在！");
        }
        String forgetToken = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if(StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("token无效或过期！");
        }
        if(forgetToken.equals(token)){
            int rowCount = userMapper.updatePwdByUsername(username, MD5Util.MD5EncodeUtf8(newPassword));
            if(rowCount > 0)
                return ServerResponse.createBySuccessMessage("重置密码成功！");
        }else{
            return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token！");
        }
        return ServerResponse.createByErrorMessage("重置密码失败！");
    }


    @Override
    public ServerResponse<List<User>> getAllUser() {
        List<User> userList = userMapper.selectAllUser();
        if(userList.isEmpty())
            return ServerResponse.createByErrorMessage("当前用户数量为空！");
        return ServerResponse.createBySuccess(userList);
    }
}
