package com.example.jenice.myplanapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.mine.MineCreateApply;
import com.example.jenice.myplanapp.mine.MineMessage;
import com.example.jenice.myplanapp.mine.MinePersonal;
import com.example.jenice.myplanapp.mine.MinePlan;
import com.example.jenice.myplanapp.mine.MinePocket;
import com.example.jenice.myplanapp.mine.MineSetting;
import com.example.jenice.myplanapp.task.updatePlanImgTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class mineFragment extends Fragment {

    private View view;
    private RelativeLayout rlSetting,rlPocket,rlMessage,rlCollect,rlCreate,rlPlan,rlPersonal;
    private TextView tvUsername;
    private ImageView ivBackgroung,ivPhoto;

    private AVUser user = AVUser.getCurrentUser();

    public mineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_mine, container, false);
        rlSetting = (RelativeLayout) view.findViewById(R.id.rl_mine_function_setting);
        rlPocket = (RelativeLayout) view.findViewById(R.id.rl_mine_function_pocket);
        rlMessage = (RelativeLayout) view.findViewById(R.id.rl_mine_function_message);
        rlCollect = (RelativeLayout) view.findViewById(R.id.rl_mine_function_collect);
        rlCreate = (RelativeLayout) view.findViewById(R.id.rl_mine_function_create);
        rlPlan = (RelativeLayout) view.findViewById(R.id.rl_mine_function_plan);
        rlPersonal = (RelativeLayout) view.findViewById(R.id.rl_mine_personal);
        tvUsername = (TextView) view.findViewById(R.id.tv_mine_name);
        ivPhoto = (ImageView) view.findViewById(R.id.iv_mine_photo);
        ivBackgroung = (ImageView) view.findViewById(R.id.iv_mine_backgound);

        tvUsername.setText(user.getUsername());
        //加载用户的头像
        String cql1 = "select url from _File where name=?";
        AVQuery.doCloudQueryInBackground(cql1, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                //显示用户头像中最新的一张，后台管理员删除旧图像
                String url =  avCloudQueryResult.getResults().get(avCloudQueryResult.getResults().size()-1).getString("url");
                //开启异步线程加载图片
                new updatePlanImgTask(ivPhoto).execute(url,user.getUsername());
            }
        }, user.getUsername());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置部分
        rlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_setting = new Intent(getActivity(),MineSetting.class);
                getActivity().finish();
                startActivity(intent_setting);
            }
        });
        //钱包部分
        rlPocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_pocket = new Intent(getActivity(), MinePocket.class);
                getActivity().finish();
                startActivity(intent_pocket);
            }
        });
        //消息部分
        rlMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_message = new Intent(getActivity(), MineMessage.class);
                getActivity().finish();
                startActivity(intent_message);
            }
        });
        //收藏部分
        rlCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //创建计划部分
        rlCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_create = new Intent(getActivity(), MineCreateApply.class);
                getActivity().finish();
                startActivity(intent_create);
            }
        });
        //我的计划部分
        rlPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_plan = new Intent(getActivity(), MinePlan.class);
                getActivity().finish();
                startActivity(intent_plan);
            }
        });
        //个人资料部分
        rlPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_personal = new Intent(getActivity(), MinePersonal.class);
                getActivity().finish();
                startActivity(intent_personal);
            }
        });
    }
}
