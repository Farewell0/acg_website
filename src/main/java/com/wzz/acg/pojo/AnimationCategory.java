package com.wzz.acg.pojo;

import java.util.Date;

public class AnimationCategory {
    private Integer id;

    private String categoryName;

    private String status;

    private Date createTime;

    private Date updateTime;

    public AnimationCategory(Integer id, String categoryName, String status, Date createTime, Date updateTime) {
        this.id = id;
        this.categoryName = categoryName;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public AnimationCategory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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