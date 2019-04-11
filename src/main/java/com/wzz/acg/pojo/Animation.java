package com.wzz.acg.pojo;

import java.util.Date;

public class Animation {
    private Integer id;

    private String animationName;

    private Integer status;

    private Date releaseTime;

    private Integer episodeNumber;

    private Integer categoryId;

    private String desc;

    private String mainImage;

    private String score;

    private Date createTime;

    private Date updateTime;

    public Animation(Integer id, String animationName, Integer status, Date releaseTime, Integer episodeNumber, Integer categoryId, String desc, String mainImage, String score, Date createTime, Date updateTime) {
        this.id = id;
        this.animationName = animationName;
        this.status = status;
        this.releaseTime = releaseTime;
        this.episodeNumber = episodeNumber;
        this.categoryId = categoryId;
        this.desc = desc;
        this.mainImage = mainImage;
        this.score = score;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Animation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName == null ? null : animationName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
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