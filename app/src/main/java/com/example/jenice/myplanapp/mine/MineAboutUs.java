package com.example.jenice.myplanapp.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.jenice.myplanapp.R;

public class MineAboutUs extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_about_us);

        ivBack = (ImageView) findViewById(R.id.iv_mine_aboutus_back);

        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_mine_aboutus_back:
                Intent intent_back = new Intent(MineAboutUs.this, MineSetting.class);
                finish();
                startActivity(intent_back);
                break;
        }
    }
}
