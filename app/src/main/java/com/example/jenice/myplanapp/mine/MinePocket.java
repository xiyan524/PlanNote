package com.example.jenice.myplanapp.mine;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;

public class MinePocket extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack,ivMedal1,ivMedal2,ivMedal3,ivMedal4,ivMedal5,ivMedal6;
    private TextView tvMedalIntro,tvMoney;
    private AVUser user = AVUser.getCurrentUser();
    private Dialog dialog1,dialog2,dialog3,dialog4,dialog5,dialog6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_pocket);

        ivBack = (ImageView) findViewById(R.id.iv_mine_pocket_back);
        ivMedal1 = (ImageView) findViewById(R.id.iv_mine_pocket_medal_1);
        ivMedal2 = (ImageView) findViewById(R.id.iv_mine_pocket_medal_2);
        ivMedal3 = (ImageView) findViewById(R.id.iv_mine_pocket_medal_3);
        ivMedal4 = (ImageView) findViewById(R.id.iv_mine_pocket_medal_4);
        ivMedal5 = (ImageView) findViewById(R.id.iv_mine_pocket_medal_5);
        ivMedal6 = (ImageView) findViewById(R.id.iv_mine_pocket_medal_6);
        tvMedalIntro = (TextView) findViewById(R.id.tv_mine_pocket_medal_intro);
        tvMoney = (TextView) findViewById(R.id.tv_mine_pocket_money_show);

        ivBack.setOnClickListener(this);
        ivMedal1.setOnClickListener(this);
        ivMedal2.setOnClickListener(this);
        ivMedal3.setOnClickListener(this);
        ivMedal4.setOnClickListener(this);
        ivMedal5.setOnClickListener(this);
        ivMedal6.setOnClickListener(this);
        tvMedalIntro.setOnClickListener(this);

        tvMoney.setText(user.getString("coin"));
        setMedalState();

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("收藏达人");
        builder1.setMessage("当你收藏计划达到五个的时候，该勋章就会点亮！");
        builder1.setPositiveButton("了解了",new ExitOnClickListeningImpl());
        this.dialog1 = builder1.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("人气爆棚");
        builder2.setMessage("当你收到20个点赞时，该勋章就会点亮！");
        builder2.setPositiveButton("了解了",new ExitOnClickListeningImpl());
        this.dialog2 = builder2.create();
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setTitle("计划星球上的学霸");
        builder3.setMessage("当你完成五个计划的时候，该勋章就会点亮！");
        builder3.setPositiveButton("了解了",new ExitOnClickListeningImpl());
        this.dialog3 = builder3.create();
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        builder4.setTitle("外出小能手");
        builder4.setMessage("当你完成十个计划的时候，该勋章就会点亮！");
        builder4.setPositiveButton("了解了",new ExitOnClickListeningImpl());
        this.dialog4 = builder4.create();
        AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
        builder5.setTitle("打卡狂魔");
        builder5.setMessage("当你每天打卡连续30天的时候，该勋章就会点亮！");
        builder5.setPositiveButton("了解了",new ExitOnClickListeningImpl());
        this.dialog5 = builder5.create();
        AlertDialog.Builder builder6 = new AlertDialog.Builder(this);
        builder6.setTitle("我行我素");
        builder6.setMessage("当你成功创建十个计划的时候，该勋章就会点亮！");
        builder6.setPositiveButton("了解了",new ExitOnClickListeningImpl());
        this.dialog6 = builder6.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_mine_pocket_back:
                Intent intent_back = new Intent(MinePocket.this, MainActivity.class);
                intent_back.putExtra("main",3);
                startActivity(intent_back);
                break;
            //点击文字查看如何获取勋章
            case R.id.tv_mine_pocket_medal_intro:
                Toast.makeText(getApplicationContext(), "获取勋章的规则有待开发！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_mine_pocket_medal_1:
                this.dialog1.show();
                break;
            case R.id.iv_mine_pocket_medal_2:
                this.dialog2.show();
                break;
            case R.id.iv_mine_pocket_medal_3:

                this.dialog3.show();
                break;
            case R.id.iv_mine_pocket_medal_4:

                this.dialog4.show();
                break;
            case R.id.iv_mine_pocket_medal_5:

                this.dialog5.show();
                break;
            case R.id.iv_mine_pocket_medal_6:

                this.dialog6.show();
                break;
        }
    }

    //设置勋章的亮暗
    private void setMedalState(){
        if (user.getBoolean("isMedal1")==true)
            ivMedal1.setImageResource(R.drawable.medal1_light);
        if (user.getBoolean("isMedal2")==true)
            ivMedal2.setImageResource(R.drawable.medal2_light);
        if (user.getBoolean("isMedal3")==true)
            ivMedal3.setImageResource(R.drawable.medal3_light);
        if (user.getBoolean("isMedal4")==true)
            ivMedal4.setImageResource(R.drawable.medal4_light);
        if (user.getBoolean("isMedal5")==true)
            ivMedal5.setImageResource(R.drawable.medal5_light);
        if (user.getBoolean("isMedal6")== true)
            ivMedal6.setImageResource(R.drawable.medal6_light);
    }

    private class ExitOnClickListeningImpl implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == dialog1.BUTTON_POSITIVE){
                dialog1.dismiss();
            }
            if (which == dialog2.BUTTON_POSITIVE){
                dialog2.dismiss();
            }
            if (which == dialog3.BUTTON_POSITIVE){
                dialog3.dismiss();
            }
            if (which == dialog4.BUTTON_POSITIVE){
                dialog4.dismiss();
            }
            if (which == dialog5.BUTTON_POSITIVE){
                dialog5.dismiss();
            }
            if (which == dialog6.BUTTON_POSITIVE){
                dialog6.dismiss();
            }
        }
    }
}
