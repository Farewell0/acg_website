package com.wzz.acg.pojo;

import java.util.Date;

public class AnimationComment {
    private Integer id;

    private Integer animationId;

    private Integer commentatorId;

    private String commentatorName;

    private String detail;

    private Date createTime;

    private Date updateTime;

    public AnimationComment(Integer id, Integer animationId, Integer commentatorId, String commentatorName, String detail, Date createTime, Date updateTime) {
        this.id = id;
        this.animationId = animationId;
        this.commentatorId = commentatorId;
        this.commentatorName = commentatorName;
        this.detail = detail;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public AnimationComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnimationId() {
        return animationId;
    }

    public void setAnimationId(Integer animationId) {
        this.animationId = animationId;
    }

    public Integer getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(Integer commentatorId) {
        this.commentatorId = commentatorId;
    }

    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName == null ? null : commentatorName.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}