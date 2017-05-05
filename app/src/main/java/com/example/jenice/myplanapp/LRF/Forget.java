package com.example.jenice.myplanapp.LRF;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.example.jenice.myplanapp.R;

public class Forget extends AppCompatActivity implements View.OnClickListener{

    private EditText edtCheck,edtPassword,edtPasswordAgain,edtTele;
    private Button btnOk,btnSend;
    private ImageView ivBack,ivEye1,ivEye2;

    private String password,passAgain,check,tele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        edtCheck = (EditText) findViewById(R.id.et_forget_check);
        edtTele = (EditText) findViewById(R.id.et_forget_tele);
        edtPassword = (EditText) findViewById(R.id.et_forget_password);
        edtPasswordAgain = (EditText) findViewById(R.id.et_forget_password_again);
        btnOk = (Button) findViewById(R.id.btn_forget_ok);
        btnSend = (Button) findViewById(R.id.btn_forget_send);
        ivBack = (ImageView) findViewById(R.id.iv_forget_back);
        ivEye1 = (ImageView) findViewById(R.id.iv_forget_eye_1);
        ivEye2 = (ImageView) findViewById(R.id.iv_forget_eye_2);

        btnOk.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivEye1.setOnClickListener(this);
        ivEye2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_forget_ok:
                //确认修改密码
                tele = edtTele.getText().toString();
                check = edtCheck.getText().toString();
                password = edtPassword.getText().toString();
                passAgain = edtPasswordAgain.getText().toString();
                //检查用户名、密码、确认密码是否填写
                if(check.length()==0){
                    Toast.makeText(getApplicationContext(),"请填写短信验证码!",Toast.LENGTH_SHORT).show();
                }else if (password.length()==0||passAgain.length()==0){
                    Toast.makeText(getApplicationContext(),"请填写密码!",Toast.LENGTH_SHORT).show();
                }else if(tele.length()==0) {
                    Toast.makeText(getApplicationContext(), "请填写绑定的手机号!", Toast.LENGTH_SHORT).show();
                }else{
                    if (!password.equals(passAgain)){
                        Toast.makeText(getApplicationContext(),"两次密码输入不相同，请重新输入！",Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");
                        edtPasswordAgain.setText("");
                    }else {
                        modifyPassword();
                    }
                }
                break;
            case R.id.iv_forget_back:
                //返回登录界面
                Intent intent = new Intent(Forget.this, Login.class);
                finish();
                startActivity(intent);
                break;
            case R.id.iv_forget_eye_1:
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.iv_forget_eye_2:
                edtPasswordAgain.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.btn_forget_send:
                tele = edtTele.getText().toString();
                requestModifyPassword();
                btnSend.setVisibility(View.INVISIBLE);
                break;
        }
    }

    //修改密码
    private void modifyPassword(){
        AVUser.resetPasswordBySmsCodeInBackground(check, password, new UpdatePasswordCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    //验证码成功
                    startActivity(new Intent(Forget.this,Login.class));
                }else {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //请求短信修改密码
    private void requestModifyPassword(){
        AVUser.requestPasswordResetBySmsCodeInBackground(tele, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    Toast.makeText(getApplicationContext(),"短信验证码发送成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
