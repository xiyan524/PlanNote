package com.example.jenice.myplanapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.entity.PlanDetail;

import java.util.List;

/**
 * Created by Jenice on 2017/4/24.
 */

public class PlanItemAdapter extends ArrayAdapter<PlanDetail> {
    private int resourceId;
    private PlanDetail Plandetail;

    public PlanItemAdapter(Context context, int textViewResourceId, List<PlanDetail> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plandetail=getItem(position);
        //初始化listView的子布局
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);

        TextView DayNum = (TextView) view.findViewById(R.id.plan_detail_item_daynum);
        TextView DayContent = (TextView) view.findViewById(R.id.plan_detail_item_daycontent);
        DayNum.setText(Plandetail.getPlanDay()+"");
        DayContent.setText(Plandetail.getPlanContent());
        return view;
    }
}

