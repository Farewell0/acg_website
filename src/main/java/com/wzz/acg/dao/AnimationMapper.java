package com.wzz.acg.dao;

import com.wzz.acg.pojo.Animation;

public interface AnimationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Animation record);

    int insertSelective(Animation record);

    Animation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Animation record);

    int updateByPrimaryKey(Animation record);
}