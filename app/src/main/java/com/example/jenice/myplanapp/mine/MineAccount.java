package com.example.jenice.myplanapp.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;

public class MineAccount extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_account);

        ivBack = (ImageView) findViewById(R.id.iv_mine_account_back);

        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_mine_account_back:
                Intent intent_back = new Intent(MineAccount.this, MinePersonal.class);
                finish();
                startActivity(intent_back);
                break;
        }
    }
}
