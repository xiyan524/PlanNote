package com.example.jenice.myplanapp.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jenice.myplanapp.R;

public class MineSuggest extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private EditText etSuggest1,etSuggest2;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_suggest);

        ivBack = (ImageView) findViewById(R.id.iv_mine_suggest_back);
        etSuggest1 = (EditText) findViewById(R.id.edt_mine_suggest_1);
        etSuggest2 = (EditText) findViewById(R.id.edt_mine_suggest_2);
        btnSubmit = (Button) findViewById(R.id.btn_mine_suggest_submit);

        btnSubmit.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_mine_suggest_back:
                finish();
                startActivity(new Intent(MineSuggest.this,MineSetting.class));
                break;
            case R.id.btn_mine_suggest_submit:
                String info = "您的建议已经反馈给我们，我们会尽快回复给您！";
                String warn = "请您完整填写建议和联系方式!";
                if (etSuggest1.getText().toString().length()!=0 && etSuggest2.getText().toString().length()!=0)
                    Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),warn,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
