package com.example.jenice.myplanapp.entity;

import com.avos.avoscloud.AVObject;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/4.
 */

public class Card extends AVObject{
    private String UserName;
    private String UserInfo;
    private String Catagory;
    private String PraiseNum;
    private String CommentNum;
    private int headImg;
    private int mainImg;
    private Date updatedAt;
    private Date createdAt;
    private String PlanName;
    private String PlanDay;

    public Card(){super();}


    public Card(String userName, Date updatedAt, String userInfo, String catagory, String praiseNum,
                String commentNum, int headImg, int mainImg) {

        this.UserName = userName;
        this.updatedAt = updatedAt;
        this.UserInfo = userInfo;
        this.Catagory = catagory;
        this.PraiseNum = praiseNum;
        this.CommentNum = commentNum;
        this.headImg = headImg;
        this.mainImg = mainImg;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public String getPlanDay() {
        return PlanDay;
    }

    public void setPlanDay(String planDay) {
        PlanDay = planDay;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(String userInfo) {
        UserInfo = userInfo;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    public String getPraiseNum() {
        return PraiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        PraiseNum = praiseNum;
    }

    public String getCommentNum() {
        return CommentNum;
    }

    public void setCommentNum(String commentNum) {
        CommentNum = commentNum;
    }

    public int getHeadImg() {
        return headImg;
    }

    public void setHeadImg(int headImg) {
        this.headImg = headImg;
    }

    public int getMainImg() {
        return mainImg;
    }

    public void setMainImg(int mainImg) {
        this.mainImg = mainImg;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
