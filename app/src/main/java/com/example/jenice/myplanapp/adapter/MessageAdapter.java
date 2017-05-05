package com.example.jenice.myplanapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.entity.Message;

import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class MessageAdapter extends ArrayAdapter<Message> {
    private Message message;
    private int resourceid;

    public MessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        resourceid = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        message = getItem(position);
        //初始化子布局
        View view= LayoutInflater.from(getContext()).inflate(resourceid,null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_item_mine_message_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_item_mine_message_content);

        tvTitle.setText(message.getName());
        tvContent.setText(message.getContent());

        return view;
    }
}

