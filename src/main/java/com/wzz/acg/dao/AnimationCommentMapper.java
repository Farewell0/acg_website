package com.wzz.acg.dao;

import com.wzz.acg.pojo.AnimationComment;

public interface AnimationCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnimationComment record);

    int insertSelective(AnimationComment record);

    AnimationComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnimationComment record);

    int updateByPrimaryKey(AnimationComment record);
}