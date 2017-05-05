package com.example.jenice.myplanapp.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.adapter.PlanAdapter;
import com.example.jenice.myplanapp.entity.Participate;
import com.example.jenice.myplanapp.entity.Plan;
import com.example.jenice.myplanapp.home.PlanDetails;

import java.util.ArrayList;
import java.util.List;

public class MinePlan extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Plan> planlist = new ArrayList<Plan>();
    private List<Participate> participateList = new ArrayList<Participate>();
    private List<Plan> planList = new ArrayList<Plan>();
    private ListView listView;
    private PlanAdapter adapter;
    private AVUser user = AVUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_plan);

        listView = (ListView) findViewById(R.id.lv_mine_plan);
        listView.setOnItemClickListener(this);

        String cql = "select * from Participate";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                participateList = (List<Participate>) avCloudQueryResult.getResults();
                Log.d("","");
            }
        }, Participate.class);

        cql = "select * from Plan where PlanName = ?";
        for(Participate participate:participateList){
            AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
                @Override
                public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                    List<Plan> tempList = (List<Plan>) avCloudQueryResult.getResults();
                    planlist.add(tempList.get(0));
                    adapter = new PlanAdapter(getApplicationContext(), R.layout.plan_item, planList);
                    listView.setAdapter(adapter);
                }
            }, Plan.class,participate.getPlanName());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //传递数据给plan_detail的activity
        Bundle bundle = new Bundle();
        Plan chooseplan = planlist.get(position);
        bundle.putString("PlanName", chooseplan.getPlanName());
        bundle.putString("PlanDeclaration",chooseplan.getPlanDeclaration());
        bundle.putString("UserName",chooseplan.getUserName());

        Intent intent = new Intent(getApplicationContext(), PlanDetails.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
