package com.example.jenice.myplanapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.task.updatePlanImgTask;

/**
 * Created by Jenice on 2017/4/24.
 */

public class DayDuty extends AppCompatActivity {
    TextView duty_instruction;
    AVUser user = AVUser.getCurrentUser();
    String plan_name;
    int plan_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_duty);

        Bundle bundle = this.getIntent().getExtras();
        plan_day=bundle.getInt("PlanDay");
        String detail_name=bundle.getString("PlanDetailName");
        plan_name=bundle.getString("PlanName");

        TextView duty_day= (TextView) findViewById(R.id.day_duty_daynum);
        duty_day.setText("DAY"+plan_day);
        TextView duty = (TextView) findViewById(R.id.day_duty_declaration2);
        duty.setText(detail_name);
        TextView duty_plan_name= (TextView) findViewById(R.id.day_duty_plan_name);
        duty_plan_name.setText(plan_name);

        //任务的具体内容
        duty_instruction= (TextView) findViewById(R.id.day_duty_content);
        String cql = "select PlanInstruction from PlanDetail where PlanName = ? and PlanDay=?";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                String content = avCloudQueryResult.getResults().get(0).getString("PlanInstruction");
                duty_instruction.setText(content);
            }
        },plan_name,plan_day);

        //某天计划的图片
        final ImageView imageview= (ImageView) findViewById(R.id.day_duty_img);
        final String tmp_image=plan_name+plan_day+".jpg";
        String cql1 = "select url from _File where name=?";
        AVQuery.doCloudQueryInBackground(cql1, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                String url =  avCloudQueryResult.getResults().get(0).getString("url");
                //开启异步线程加载图片
                new updatePlanImgTask(imageview).execute(url,tmp_image);
            }
        }, tmp_image);

        Button btn_signin = (Button) findViewById(R.id.day_duty_btn);

    }

    //获取用户打卡的计划ID
    public void sign_in(View view){
        //根据用户名和计划名获取ID
        String cql = "select objectId,Schedule from Participate where UserName=? and PlanName=?";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                String objectId =  avCloudQueryResult.getResults().get(0).getObjectId();
                int Schedule = avCloudQueryResult.getResults().get(0).getInt("Schedule");
                boolean flag=check_sign_in(Schedule);
                sign_in_help(objectId,Schedule ,flag);
            }
        },user.getUsername(),plan_name);
    }

    //根据ID跟新数据
    public void sign_in_help(String objectId,int Schedule,boolean flag){
        if(flag==true){
            AVObject participate = AVObject.createWithoutData("Participate",objectId);
            participate.put("Schedule",Schedule+1);
            participate.saveInBackground();

            Bundle bundle = new Bundle();
            bundle.putString("UserName",user.getUsername());
            bundle.putString("PlanName",plan_name);
            bundle.putInt("Day",Schedule+1);
            Intent intent = new Intent(DayDuty.this, SignIn.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else
            Toast.makeText(this,"你之前的打卡还未完成",Toast.LENGTH_LONG).show();
    }

    //判断当前打卡是否成立
    public boolean check_sign_in(int schedule){
        if(schedule==plan_day-1)
            return true;
        else
            return false;
    }

}

