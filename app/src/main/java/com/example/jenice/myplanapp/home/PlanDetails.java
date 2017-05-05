package com.example.jenice.myplanapp.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.adapter.PlanItemAdapter;
import com.example.jenice.myplanapp.entity.PlanDetail;
import com.example.jenice.myplanapp.task.updatePlanImgTask;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import static com.example.jenice.myplanapp.R.id.btn_collect_plan;

/**
 * Created by Jenice on 2017/4/24.
 */

public class PlanDetails extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String plan_name;
    ImageView PlanImg;
    ImageView PersonImg;
    TextView UserGoal;
    List<PlanDetail> detail_list = new ArrayList<PlanDetail>();
    PlanItemAdapter adapter;
    ListView plan_detail_list;
    Context context;
    Button btn_join, btn_collection;

    AVUser user = AVUser.getCurrentUser();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_details);
        context = this;

        //接收上一个界面的传递参数
        Bundle bundle = this.getIntent().getExtras();
        plan_name = bundle.getString("PlanName");
        String plan_declaration = bundle.getString("PlanDeclaration");
        final String user_name = bundle.getString("UserName");

        //计划相关内容
        TextView PlanName = (TextView) findViewById(R.id.plan_detail_name);
        TextView PlanDeclaration = (TextView) findViewById(R.id.plan_detail_declaration);
        PlanImg = (ImageView) findViewById(R.id.plan_detail_img);
        PlanName.setText(plan_name);
        PlanDeclaration.setText(plan_declaration);
        String cql = "select url from _File where name=?";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                String url = avCloudQueryResult.getResults().get(0).getString("url");
                //开启异步线程加载图片
                new updatePlanImgTask(PlanImg).execute(url, plan_name);
            }
        }, plan_name);

        //计划创建用户的相关内容
        TextView UserNickName = (TextView) findViewById(R.id.plan_detail_personname);
        UserNickName.setText(user_name);
        UserGoal = (TextView) findViewById(R.id.plan_detail_person_decla);
        PersonImg = (ImageView) findViewById(R.id.plan_detail_photo);
        //加载用户头像
        String cql1 = "select url from _File where name=?";
        AVQuery.doCloudQueryInBackground(cql1, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                String url = avCloudQueryResult.getResults().get(0).getString("url");
                //开启异步线程加载图片
                new updatePlanImgTask(PersonImg).execute(url, user_name);
            }
        }, user_name);
        //加载用户名称和宣言
        String cql2 = "select * from _User where username=?";
        AVQuery.doCloudQueryInBackground(cql2, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult1, AVException e) {
                String goal = avCloudQueryResult1.getResults().get(0).getString("goal");
                UserGoal.setText(goal);
            }
        }, user_name);

        //加载计划列表
        plan_detail_list = (ListView) findViewById(R.id.Plan_Detail_List);
        plan_detail_list.setOnItemClickListener(this);
        String cql3 = "select * from PlanDetail where PlanName = ? order by PlanDay";
        AVQuery.doCloudQueryInBackground(cql3, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                detail_list = (List<PlanDetail>) avCloudQueryResult.getResults();
                adapter = new PlanItemAdapter(context, R.layout.plan_detail_item, detail_list);
                plan_detail_list.setAdapter(adapter);
            }
        }, PlanDetail.class, plan_name);

        //加入计划
        btn_join = (Button) findViewById(R.id.Plan_detail_join);
        btn_collection = (Button)findViewById(btn_collect_plan);
        //检测当前用户是否已经加入计划
        check_plan();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        PlanDetail choose_plan = detail_list.get(position);
        bundle.putString("PlanDetailName", choose_plan.getPlanContent());
        bundle.putInt("PlanDay", choose_plan.getPlanDay());
        bundle.putString("PlanName", plan_name);

        Intent intent = new Intent(PlanDetails.this, DayDuty.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //加入计划的响应函数
    public void joinPlan(View view) {
        //数据库加入一条参与记录
        AVObject join_plan = new AVObject("Participate");
        join_plan.put("UserName", user.getUsername());
        join_plan.put("PlanName", plan_name);
        join_plan.saveInBackground();

        //数据库加入一条消息记录
        AVObject msg_join = new AVObject("Message");
        msg_join.put("UserName", user.getUsername());
        msg_join.put("Name", "参加计划" + plan_name + "成功");
        msg_join.put("Content", "您已经成功参加计划，快去打卡吧!");
        msg_join.put("Type", "join_plan");
        msg_join.saveInBackground();

        Toast.makeText(PlanDetails.this, "跳转到我的计划界面查看", Toast.LENGTH_LONG).show();
    }

    //检测用户是否已经加入计划
    public void check_plan() {
        String cql = "select * from Participate where UserName=? and PlanName=? ";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                if (avCloudQueryResult.getResults().size()!=0) {
                    btn_join.setText("你已经加入该计划了!");
                    btn_join.setEnabled(false);
                }
            }
        }, user.getUsername(), plan_name);
    }

    //收藏计划
    public void Collection(View view) {
        AVObject collect_plan = new AVObject("Collection");
        collect_plan.put("UserName", user.getUsername());
        collect_plan.put("PlanName", plan_name);
        collect_plan.saveInBackground();
        Toast.makeText(this, "收藏计划成功", Toast.LENGTH_LONG).show();
    }


}

