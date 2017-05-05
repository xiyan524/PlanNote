package com.example.jenice.myplanapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.adapter.PlanAdapter;
import com.example.jenice.myplanapp.entity.Plan;
import com.example.jenice.myplanapp.home.PlanDetails;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<Plan> planlist = new ArrayList<Plan>();
    private boolean flag = false;
    private ListView listView;
    PlanAdapter adapter;
    View view;

    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) view.findViewById(R.id.Home_Plan_List);
        listView.setOnItemClickListener(this);

        String cql = "select * from Plan";
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                planlist = (List<Plan>)avCloudQueryResult.getResults();
                adapter = new PlanAdapter(view.getContext(), R.layout.plan_item, planlist);
                listView.setAdapter(adapter);
            }
        }, Plan.class);

        return view;

    }


    //监听事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //传递数据给plan_detail的activity
        Bundle bundle = new Bundle();
        Plan chooseplan = planlist.get(position);
        bundle.putString("PlanName", chooseplan.getPlanName());
        bundle.putString("PlanDeclaration",chooseplan.getPlanDeclaration());
        bundle.putString("UserName",chooseplan.getUserName());

        Intent intent = new Intent(getContext(), PlanDetails.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
