package com.wzz.acg.service;

import com.wzz.acg.common.ServerResponse;
import com.wzz.acg.vo.AnimationVo;

public interface AnimationService {

    ServerResponse<String> addOrUpdateAnimation(AnimationVo animationVo);
}
