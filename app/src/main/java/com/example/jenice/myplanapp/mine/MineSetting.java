package com.example.jenice.myplanapp.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.jenice.myplanapp.LRF.Login;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;

public class MineSetting extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack,ivSuggest,ivRecommend,ivAbout,ivclear,ivLogout;
    private TextView tvClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);

        ivAbout = (ImageView) findViewById(R.id.iv_mine_setting_about_go);
        ivBack = (ImageView) findViewById(R.id.iv_mine_setting_back);
        ivclear = (ImageView) findViewById(R.id.iv_mine_setting_clean_go);
        ivLogout = (ImageView) findViewById(R.id.iv_mine_setting_logout_go);
        ivRecommend = (ImageView) findViewById(R.id.iv_mine_setting_recommend_go);
        ivSuggest = (ImageView) findViewById(R.id.iv_mine_setting_suggest_go);
        tvClear = (TextView) findViewById(R.id.tv_mine_setting_clean_show);

        ivBack.setOnClickListener(this);
        ivSuggest.setOnClickListener(this);
        ivRecommend.setOnClickListener(this);
        ivclear.setOnClickListener(this);
        ivAbout.setOnClickListener(this);
        ivLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_mine_setting_back:
                //返回我的界面
                Intent intent_back = new Intent(MineSetting.this,MainActivity.class);
                intent_back.putExtra("main",3);
                finish();
                startActivity(intent_back);
                break;
            case R.id.iv_mine_setting_suggest_go:
                //跳转到意见反馈界面
                Intent intent_suggest = new Intent(MineSetting.this,MineSuggest.class);
                finish();
                startActivity(intent_suggest);
                break;
            case R.id.iv_mine_setting_about_go:
                //跳转到关于我们界面
                Intent intent_aboutus = new Intent(MineSetting.this,MineAboutUs.class);
                finish();
                startActivity(intent_aboutus);
                break;
            case R.id.iv_mine_setting_recommend_go:
                //分享App
                Toast.makeText(getApplicationContext(), "分享到第三方平台待开发，敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_mine_setting_clean_go:
                //清理缓存
                tvClear.setText("0.0B");
                Toast.makeText(getApplicationContext(),"清理成功！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_mine_setting_logout_go:
                //注销
                AVUser.getCurrentUser().logOut();
                finish();
                startActivity(new Intent(MineSetting.this, Login.class));
                break;
        }
    }
}
