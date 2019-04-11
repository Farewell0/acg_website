package com.wzz.acg.dao;

import com.wzz.acg.pojo.AnimationCategory;

public interface AnimationCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnimationCategory record);

    int insertSelective(AnimationCategory record);

    AnimationCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnimationCategory record);

    int updateByPrimaryKey(AnimationCategory record);
}