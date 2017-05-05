package com.example.jenice.myplanapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.entity.Plan;
import com.example.jenice.myplanapp.task.updatePlanImgTask;

import java.util.List;

/**
 * Created by Jenice on 2017/4/24.
 */

public class PlanAdapter extends ArrayAdapter<Plan> {
    private int resourceId;
    private Plan plan;
    private ImageView PlanImg;


    public PlanAdapter(Context context, int textViewResourceId, List<Plan> objects) {
        super(context, textViewResourceId, objects);
        //重写父类的构造函数，用于将上下文、listview子项布局的id和数据传入
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取当前的Plan实例
        plan=getItem(position);
        //初始化listView的子布局
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);

        final ImageView PlanImg= (ImageView) view.findViewById(R.id.img_planitem__plan1);
        TextView PlanName= (TextView) view.findViewById(R.id.item_plan_plan_name);
        TextView PlanDeclaration= (TextView) view.findViewById(R.id.item_plan_plan_declaration);

        String cql = "select url from _File where name=?";
        final String plan_name=plan.getPlanName();
        AVQuery.doCloudQueryInBackground(cql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                String url =  avCloudQueryResult.getResults().get(0).getString("url");
                //开启异步线程加载图片
                new updatePlanImgTask(PlanImg).execute(url,plan_name);
            }
        }, plan.getPlanName());

        PlanName.setText(plan.getPlanName());
        PlanDeclaration.setText(plan.getPlanDeclaration());
        return view;
    }
}

