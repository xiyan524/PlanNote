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
import com.avos.avoscloud.SignUpCallback;
import com.example.jenice.myplanapp.R;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText edtUsername,edtPassword,edtPasswordAgain,edtTele;
    private Button btnOk,btnCancel;
    private ImageView ivEye1,ivEye2;

    private String username,password,passAgain,tele;
    private AVUser user = new AVUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = (EditText) findViewById(R.id.et_register_phone);
        edtPassword = (EditText) findViewById(R.id.et_register_password);
        edtPasswordAgain = (EditText) findViewById(R.id.et_register_password_again);
        edtTele = (EditText) findViewById(R.id.et_register_tele);
        btnOk = (Button) findViewById(R.id.btn_register_ok);
        btnCancel = (Button) findViewById(R.id.btn_register_cancel);
        ivEye1 = (ImageView) findViewById(R.id.iv_register_eye_1);
        ivEye2 = (ImageView) findViewById(R.id.iv_register_eye_2);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        ivEye2.setOnClickListener(this);
        ivEye1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_register_ok:
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                passAgain = edtPasswordAgain.getText().toString();
                tele = edtTele.getText().toString();
                //检查用户名、密码、确认密码是否填写
                if(username.length()==0){
                    Toast.makeText(getApplicationContext(),"请填写用户名!",Toast.LENGTH_SHORT).show();
                }else if (password.length()==0||passAgain.length()==0){
                    Toast.makeText(getApplicationContext(),"请填写密码!",Toast.LENGTH_SHORT).show();
                }else if (tele.length()==0){
                    Toast.makeText(getApplicationContext(),"请填写手机号!",Toast.LENGTH_SHORT).show();
                }else{
                    if (!password.equals(passAgain)){
                        Toast.makeText(getApplicationContext(),"两次密码输入不相同，请重新输入！",Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");
                        edtPasswordAgain.setText("");
                    }else {
                        userRegister();
                    }
                }
                break;
            case R.id.btn_register_cancel:
                //相当于返回到登录界面
                Intent intent = new Intent(Register.this, Login.class);
                finish();
                startActivity(intent);
                break;
            case R.id.iv_register_eye_1:
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.iv_register_eye_2:
                edtPasswordAgain.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
        }
    }

    //用户根据用户名、手机号和密码进行注册
    private void userRegister(){
        user.setUsername(username);
        user.setPassword(password);
        user.setMobilePhoneNumber(tele);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    //注册成功，将用户对象赋给当前用户AVUser.getcurrentUSer()
                    Intent intent_suc = new Intent(Register.this,CheckPhone.class);
                    intent_suc.putExtra("tele",tele);
                    finish();
                    startActivity(intent_suc);
                }else if (e.getCode()==202){
                    //用户名已经存在
                    Toast.makeText(getApplicationContext(),"用户名已存在！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
