package com.example.jenice.myplanapp.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.adapter.MessageAdapter;
import com.example.jenice.myplanapp.entity.Message;
import com.example.jenice.myplanapp.fragment.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MineMessage extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private AVUser user = AVUser.getCurrentUser();
    private List<Message> messageList = new ArrayList<Message>();
    private ListView lvMessage;
    MessageAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message);
        context = this;

        ivBack = (ImageView) findViewById(R.id.iv_mine_message_back);
        lvMessage = (ListView) findViewById(R.id.lv_mine_message);
        ivBack.setOnClickListener(this);

        String sql = "select * from Message where UserName = ?";
        AVQuery.doCloudQueryInBackground(sql, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                messageList = (List<Message>) avCloudQueryResult.getResults();
                adapter = new MessageAdapter(context,R.layout.item_mine_message,messageList);
                lvMessage.setAdapter(adapter);
            }
        },Message.class,user.getUsername());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_mine_message_back:
                startActivity(new Intent(MineMessage.this, MainActivity.class).putExtra("main",3));
                break;
        }
    }
}
