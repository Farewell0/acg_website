package com.wzz.acg.service.impl;

import com.wzz.acg.common.ServerResponse;
import com.wzz.acg.dao.AnimationMapper;
import com.wzz.acg.pojo.Animation;
import com.wzz.acg.service.AnimationService;
import com.wzz.acg.util.DateTimeUtil;
import com.wzz.acg.vo.AnimationVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("/animationService")
public class AnimationServiceImpl implements AnimationService {

    @Autowired
    private AnimationMapper animationMapper;

    @Override
    public ServerResponse<String> addOrUpdateAnimation(AnimationVo animationVo){
        Animation animation = new Animation();
        if(StringUtils.isNotEmpty(animationVo.getAnimationName()))
            animation.setAnimationName(animationVo.getAnimationName());
        if(null != animationVo.getStatus())
            animation.setStatus(animationVo.getStatus());
        if(StringUtils.isNotEmpty(animationVo.getReleaseTime()))
            animation.setReleaseTime(DateTimeUtil.strToDate(animationVo.getReleaseTime()));
        if(null != animationVo.getEpisodeNumber())
            animation.setEpisodeNumber(animationVo.getEpisodeNumber());
        if(null != animationVo.getCategoryId())
            animation.setCategoryId(animationVo.getCategoryId());
        if(StringUtils.isNotEmpty(animationVo.getDesc()))
            animation.setDesc(animationVo.getDesc());
        if(StringUtils.isNotEmpty(animationVo.getMainImage()))
            animation.setMainImage(animationVo.getMainImage());

        if(animationVo.getId() == null) {
            int rowCount = animationMapper.insert(animation);
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("添加动画成功！");
            }
            return ServerResponse.createByErrorMessage("添加动画失败！");
        }else{
            Animation a = animationMapper.selectByPrimaryKey(animationVo.getId());
            if(a == null){
                return ServerResponse.createByErrorMessage("要修改的动画不存在，可能已下架！");
            }
            animation.setId(animationVo.getId());
            animation.setCreateTime(a.getCreateTime());
            int rowCount = animationMapper.updateByPrimaryKeySelective(animation);
            if(rowCount > 0)
                return ServerResponse.createBySuccessMessage("更新动画信息成功！");
            return ServerResponse.createByErrorMessage("更新动画信息失败！");
        }
    }
}

