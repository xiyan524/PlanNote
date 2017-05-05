package com.example.jenice.myplanapp.LRF;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;

public class CheckPhone extends AppCompatActivity implements View.OnClickListener{

    private Button btnSend,btnNext;
    private EditText edtCheck;
    private ImageView ivBack;
    private String tele,check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_phone);

        ivBack = (ImageView) findViewById(R.id.iv_check_phone_back);
        btnNext = (Button) findViewById(R.id.btn_check_phone_next);
        btnSend = (Button) findViewById(R.id.btn_check_phone_send);
        edtCheck = (EditText) findViewById(R.id.et_check_phone_check);

        ivBack.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        Intent intent = this.getIntent();
        tele = intent.getStringExtra("tele");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_check_phone_back:
                startActivity(new Intent(CheckPhone.this,Register.class));
                finish();
                break;
            case R.id.btn_check_phone_next:
                check = edtCheck.getText().toString();
                if (check.length()==0){
                    Toast.makeText(getApplicationContext(), "请填写短信验证码!", Toast.LENGTH_SHORT).show();
                }else {
                    check();
                }
                break;
            case R.id.btn_check_phone_send:
                requestCheck();
                btnSend.setVisibility(View.INVISIBLE);
                break;
        }
    }

    //请求发送验证码
    private void requestCheck(){
        AVUser.requestMobilePhoneVerifyInBackground(tele, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    Toast.makeText(getApplicationContext(), "发送成功！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //调用验证接口，检验验证码
    private void check(){
        AVUser.verifyMobilePhoneInBackground(check, new AVMobilePhoneVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    Toast.makeText(getApplicationContext(),"验证成功，准备登录！",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CheckPhone.this, MainActivity.class).putExtra("main",3));
                }else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
