package com.wzz.acg.controller;

import com.wzz.acg.common.Const;
import com.wzz.acg.common.ResponseCode;
import com.wzz.acg.common.ServerResponse;
import com.wzz.acg.pojo.User;
import com.wzz.acg.service.AnimationService;
import com.wzz.acg.vo.AnimationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("/animationController")
@RequestMapping("/animation")
public class AnimationController {

    @Autowired
    private AnimationService animationService;

    /**
     * 添加或更新一条动画信息
     * @param animationVo
     * @param session
     * @return
     */
    @RequestMapping(value = "add_update_animation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addAnimation(AnimationVo animationVo, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    ResponseCode.NEED_LOGIN.getDesc());
        if(user.getRole() != Const.Role.ROLE_ADMIN)
            return ServerResponse.createByErrorMessage("您不是管理员账号，请登录管理员操作！");

        if(animationVo != null){
            return animationService.addOrUpdateAnimation(animationVo);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                ResponseCode.ILLEGAL_ARGUMENT.getDesc());
    }
}
