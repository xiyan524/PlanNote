package com.example.jenice.myplanapp.entity;

import com.avos.avoscloud.AVObject;

/**
 * Created by Administrator on 2017/5/5.
 */

public class Participate extends AVObject {
    private String UserName;
    private Number Schedule;
    private boolean Finish;
    private String PlanName;

    public Participate() {
    }

    public Participate(String userName, int schedule, boolean finish, String planName) {
        UserName = userName;
        Schedule = schedule;
        Finish = finish;
        PlanName = planName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Number getSchedule() {
        return Schedule;
    }

    public void setSchedule(Number schedule) {
        Schedule = schedule;
    }

    public boolean isFinish() {
        return Finish;
    }

    public void setFinish(boolean finish) {
        Finish = finish;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }
}