package com.example.jenice.myplanapp.entity;

import com.avos.avoscloud.AVObject;

/**
 * Created by Jenice on 2017/4/24.
 */

public class PlanDetail extends AVObject {
    int PlanDay;
    String PlanContent;
    String PlanName;

    public PlanDetail(){
        super();
    }

    public PlanDetail(int PlanDay,String PlanName,String PlanContent){
        super();
        this.PlanContent=PlanContent;
        this.PlanDay=PlanDay;
        this.PlanName=PlanName;
    }

    public int getPlanDay() {
        return this.getInt("PlanDay");
    }

    public void setPlanDay(int planDay) {
        PlanDay = planDay;
    }

    public String getPlanContent() {
        return PlanContent;
    }

    public void setPlanContent(String planContent) {
        PlanContent = planContent;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }
}
