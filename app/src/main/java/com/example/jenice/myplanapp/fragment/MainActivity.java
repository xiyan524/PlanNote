package com.example.jenice.myplanapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jenice.myplanapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout homeLayout,activityLayout,mineLayout;
    private Fragment homeFragment,activityFragment,mineFragment,currentFragment;
    private ImageView ivHome,ivActivity,ivMine;
    private TextView tvHome,tvActivity,tvMine;
    private View viewHome,viewActivity,viewMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLabelAndText();

        //实现跳转到指定fragment
        Intent intent = getIntent();
        int id = intent.getIntExtra("main",1);
        if (id == 2)
            initTab(2);
        else if (id == 3)
            initTab(3);
        else
            initTab(1);
    }

    //初始化底部标签图标及文字,添加点击事件监听
    private void initLabelAndText() {
        homeLayout = (RelativeLayout) findViewById(R.id.rl_home);
        activityLayout = (RelativeLayout) findViewById(R.id.rl_activity);
        mineLayout = (RelativeLayout) findViewById(R.id.rl_mine);
        homeLayout.setOnClickListener(this);
        activityLayout.setOnClickListener(this);
        mineLayout.setOnClickListener(this);

        ivHome = (ImageView) findViewById(R.id.iv_tab_home);
        ivActivity = (ImageView) findViewById(R.id.iv_tab_activity);
        ivMine = (ImageView) findViewById(R.id.iv_tab_mine);
        tvHome = (TextView) findViewById(R.id.tv_tab_home);
        tvActivity = (TextView) findViewById(R.id.tv_tab_activity);
        tvMine = (TextView) findViewById(R.id.tv_tab_mine);
        viewHome = findViewById(R.id.line_home);
        viewActivity = findViewById(R.id.line_activity);
        viewMine = findViewById(R.id.line_mine);
    }

    //初始化底部tab
    private void initTab(int id) {
        switch (id){
            case 1:
                if (homeFragment == null)
                    homeFragment = new homeFragment();
                if (!homeFragment.isAdded()) {
                    //提交事务
                    getSupportFragmentManager().beginTransaction().add(R.id.content_layout, homeFragment).commit();
                    //记录当前Fragment
                    currentFragment = homeFragment;
                    //设置底部图片文本变化
                    ivHome.setImageResource(R.drawable.home_orange);
                    tvHome.setTextColor(getResources().getColor(R.color.orange));
                    viewHome.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                if (activityFragment == null)
                    activityFragment = new activityFragment();
                if (!activityFragment.isAdded()) {
                    //提交事务
                    getSupportFragmentManager().beginTransaction().add(R.id.content_layout, activityFragment).commit();
                    //记录当前Fragment
                    currentFragment = activityFragment;
                    //设置底部图片文本变化
                    ivActivity.setImageResource(R.drawable.activity_orange);
                    tvActivity.setTextColor(getResources().getColor(R.color.orange));
                    viewActivity.setVisibility(View.VISIBLE);
                }
                break;
            case 3:
                if (mineFragment == null)
                    mineFragment = new mineFragment();
                if (!mineFragment.isAdded()) {
                    //提交事务
                    getSupportFragmentManager().beginTransaction().add(R.id.content_layout, mineFragment).commit();
                    //记录当前Fragment
                    currentFragment = mineFragment;
                    //设置底部图片文本变化
                    ivMine.setImageResource(R.drawable.mine_orange);
                    tvMine.setTextColor(getResources().getColor(R.color.orange));
                    viewMine.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_home:
                clickHomeLayout();
                break;
            case R.id.rl_activity:
                clickActivityLayout();
                break;
            case R.id.rl_mine:
                clickMineLayout();
                break;
        }
    }

    //点击主页后的事件响应
    private void clickHomeLayout(){
        if (homeFragment == null)
            homeFragment = new homeFragment();
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),homeFragment);
        //设置底部图标和字体颜色
        ivHome.setImageResource(R.drawable.home_orange);
        tvHome.setTextColor(getResources().getColor(R.color.orange));
        viewHome.setVisibility(View.VISIBLE);
        ivActivity.setImageResource(R.drawable.activity_black);
        tvActivity.setTextColor(getResources().getColor(R.color.black));
        viewActivity.setVisibility(View.INVISIBLE);
        ivMine.setImageResource(R.drawable.mine_black);
        tvMine.setTextColor(getResources().getColor(R.color.black));
        viewMine.setVisibility(View.INVISIBLE);
    }

    //点击探索后的事件响应
    private void clickActivityLayout(){
        if (activityFragment == null)
            activityFragment = new activityFragment();
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),activityFragment);
        //设置底部图标和字体颜色
        ivHome.setImageResource(R.drawable.home_black);
        tvHome.setTextColor(getResources().getColor(R.color.black));
        viewHome.setVisibility(View.INVISIBLE);
        ivActivity.setImageResource(R.drawable.activity_orange);
        tvActivity.setTextColor(getResources().getColor(R.color.orange));
        viewActivity.setVisibility(View.VISIBLE);
        ivMine.setImageResource(R.drawable.mine_black);
        tvMine.setTextColor(getResources().getColor(R.color.black));
        viewMine.setVisibility(View.INVISIBLE);
    }

    //点击我的后的事件响应
    private void clickMineLayout(){
        if (mineFragment == null)
            mineFragment = new mineFragment();
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),mineFragment);
        //设置底部图标和字体颜色
        ivHome.setImageResource(R.drawable.home_black);
        tvHome.setTextColor(getResources().getColor(R.color.black));
        viewHome.setVisibility(View.INVISIBLE);
        ivActivity.setImageResource(R.drawable.activity_black);
        tvActivity.setTextColor(getResources().getColor(R.color.black));
        viewActivity.setVisibility(View.INVISIBLE);
        ivMine.setImageResource(R.drawable.mine_orange);
        tvMine.setTextColor(getResources().getColor(R.color.orange));
        viewMine.setVisibility(View.VISIBLE);
    }


    //添加显示fragment
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment){
        if(currentFragment == fragment)
            return;
        if (!fragment.isAdded()){
            transaction.hide(currentFragment).add(R.id.content_layout,fragment).commit();
        }else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

}
