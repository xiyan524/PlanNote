package com.example.jenice.myplanapp.entity;

import android.app.Application;

/**
 * Created by Jenice on 2017/4/5.
 */

public class User extends Application{
    private String nickName;    //昵称
    private String gender;      //性别
    private String goal;        //目标
    private String intro;       //自我介绍
    private int gold;           //金币
    private int userId;        //用户ID
    private String tele;       //电话
    private String qq;          //qq
    private String wechat;      //微信
    private String microblog;   //微博

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getMicroblog() {
        return microblog;
    }

    public void setMicroblog(String microblog) {
        this.microblog = microblog;
    }
}
