package com.example.jenice.myplanapp.adapter;

import android.content.Context;
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
import com.example.jenice.myplanapp.entity.Card;
import com.example.jenice.myplanapp.task.updatePlanImgTask;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CardAdapter extends ArrayAdapter<Card>{
    private int resourceId;
    View view;

    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Card card=getItem(position);
        final String mainimgname=card.getUserName()+"_"+card.getCatagory();
        final String headimgname=card.getUserName();

        if(convertView==null)
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        else
            view=convertView;

        final ImageView maingraph=(ImageView)view.findViewById(R.id.main_graph);
        final ImageView headgraph=(ImageView)view.findViewById(R.id.head_graph);
        TextView userid=(TextView)view.findViewById(R.id.user_id);
        TextView userdate=(TextView)view.findViewById(R.id.user_date);
        TextView userinro=(TextView)view.findViewById(R.id.user_intro);
        TextView praisenum=(TextView)view.findViewById(R.id.user_praisenum);
        ImageView btn_praise = (ImageView) view.findViewById(R.id.btn_praise);

		//加载动态发表人的头像
        final String img_header = card.getUserName();
        String cql1 = "select url from _File where name=?";
        AVQuery.doCloudQueryInBackground(cql1, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                String url =  avCloudQueryResult.getResults().get(0).getString("url");
                //开启异步线程加载图片
                new updatePlanImgTask(headgraph).execute(url,img_header);
            }
        }, img_header);

        //加载打卡图片
        final String file_name = card.getUserName()+"_"+card.getPlanName()+"_"+card.getPlanDay();
        String cql2 = "select url from _File where name=?";
        AVQuery.doCloudQueryInBackground(cql2, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                if(avCloudQueryResult.getResults().size()!=0){
                    String url =  avCloudQueryResult.getResults().get(0).getString("url");
                    //开启异步线程加载图片
                    new updatePlanImgTask(maingraph).execute(url,file_name);
                }
            }
        }, file_name);

        userid.setText(card.getUserName());
        Date date=card.getCreatedAt();
        userdate.setText(date.toString());
        userinro.setText("#"+card.getUserInfo()+"#");
        praisenum.setText(card.getPraiseNum());

        return view;
    }

}
