package com.wzz.acg.controller;

import com.wzz.acg.common.Const;
import com.wzz.acg.common.ResponseCode;
import com.wzz.acg.common.ServerResponse;
import com.wzz.acg.pojo.User;
import com.wzz.acg.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = userService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 注册时对用户名和邮箱,手机号是否已存在的校验
     * @param arg
     * @param type 参数类型，用户名/邮箱/手机号
     * @return
     */
    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String arg, String type) {
        return userService.checkValid(arg, type);
    }

    /**
     * 登录状态下的重置密码
     * @param session
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String oldPassword, String newPassword) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        ServerResponse<String> response = userService.resetPassword(user, oldPassword, newPassword);
        if(response.isSuccess()){
            session.removeAttribute(Const.CURRENT_USER);
        }
        return response;
    }

    /**
     * 忘记密码后的密保问题查询
     * @param username
     * @return
     */
    @RequestMapping(value = "get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetPwdGetQuestion(String username){
        return userService.getQuestion(username);
    }

    /**
     * 校验密保问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "check_question_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkQuestionAnswer(String username, String question, String answer){
        return userService.checkAnswer(username, question, answer);
    }

    /**
     * 根据token和用户名重置密码
     * @param username
     * @param newPassword
     * @param token
     * @return
     */
    @RequestMapping(value = "forget_reset_pwd.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPwd(String username, String newPassword, String token){
        return userService.forgetResetPwd(username, newPassword, token);
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do")
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
    }

    /**
     * 更新用户基本信息
     * @param session
     * @param gender
     * @param email
     * @param phone
     * @param address
     * @return
     */
    @RequestMapping(value = "update_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInfo(HttpSession session, String gender, String email, String phone, String address){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<User> serverResponse = userService.updateUserInfo(user, gender, email, phone, address);
        if(serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
        }
        return serverResponse;
    }

    @RequestMapping("list_all_user.do")
    @ResponseBody
    public ServerResponse<List<User>> listAllUser(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user.getId() == 1){
            return userService.getAllUser();
        }
        return ServerResponse.createByErrorMessage("您的权限不够！");
    }

}
