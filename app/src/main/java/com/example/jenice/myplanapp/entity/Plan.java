package com.example.jenice.myplanapp.entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by Jenice on 2017/4/24.
 */
@AVClassName("Plan")
public class Plan extends AVObject {
    private String PlanName;
    private String PlanDeclaration;
    private String PlanImageId;
    private String UserName;

    public Plan(){
        super();
    }

    public  Plan(String PlanName,String PlanDeclaration,String PlanImageId,String UserName){
        this.PlanName=PlanName;
        this.PlanDeclaration=PlanDeclaration;
        this.PlanImageId=PlanImageId;
        this.UserName=UserName;
    }


    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public String getPlanDeclaration() {
        return PlanDeclaration;
    }

    public void setPlanDeclaration(String planDeclaration) {
        PlanDeclaration = planDeclaration;
    }

    public String getPlanImageId() {
        return PlanImageId;
    }

    public void setPlanImageId(String planImageId) {
        PlanImageId = planImageId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
