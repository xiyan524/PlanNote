package com.example.jenice.myplanapp.LRF;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername,edtPassword;
    private Button btnLogin;
    private TextView tvRegister,tvForget;
    private ImageView ivQQ,ivWechat,ivBlog,ivEye;
    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.et_login_phone);
        edtPassword = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login_ok);
        tvRegister = (TextView) findViewById(R.id.tv_login_register);
        tvForget = (TextView) findViewById(R.id.tv_login_forget);
        ivQQ = (ImageView) findViewById(R.id.iv_login_others_qq);
        ivWechat = (ImageView) findViewById(R.id.iv_login_others_wechat);
        ivBlog = (ImageView) findViewById(R.id.iv_login_others_weibo);
        ivEye = (ImageView) findViewById(R.id.iv_login_eye);

        //绑定事件监听
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForget.setOnClickListener(this);
        ivQQ.setOnClickListener(this);
        ivWechat.setOnClickListener(this);
        ivBlog.setOnClickListener(this);
        ivEye.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_login_register:
                //跳转到注册界面进行注册
                Intent intent_register = new Intent(Login.this, Register.class);
                finish();
                startActivity(intent_register);
                break;
            case R.id.tv_login_forget:
                //跳转到忘记密码界面
                finish();
                startActivity(new Intent(Login.this,Forget.class));
                break;
            case R.id.btn_login_ok:
                //判断账号和密码是否正确
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                if (username.length()==0){
                    Toast.makeText(getApplicationContext(),"请填写用户名！",Toast.LENGTH_SHORT).show();
                }else if (password.length()==0){
                    Toast.makeText(getApplicationContext(),"请输入密码！",Toast.LENGTH_SHORT).show();
                }else {
                    userLogin();
                }
                break;
            case R.id.iv_login_eye:
                //设置密码框为可见
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.iv_login_others_weibo:
                Toast.makeText(getApplicationContext(), "微博登录待开发，敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_login_others_qq:
                Toast.makeText(getApplicationContext(), "QQ登录待开发，敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_login_others_wechat:
                Toast.makeText(getApplicationContext(), "微信登录待开发，敬请期待！",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //用户登录
    private void userLogin(){
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e==null){
                    Intent intent_login = new Intent(Login.this,MainActivity.class);
                    intent_login.putExtra("main",3);
                    finish();
                    startActivity(intent_login);
                }else if (e.getCode()==210){
                    Toast.makeText(getApplicationContext(),"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                    edtPassword.setText("");
                }else if (e.getCode()==211){
                    Toast.makeText(getApplicationContext(),"用户名不存在，请先注册！",Toast.LENGTH_SHORT).show();
                    edtPassword.setText("");
                }else if (e.getCode()==219){
                    Toast.makeText(getApplicationContext(),"密码尝试次数过多，请15分钟后再次尝试，或者选择注册新账号进行登录！",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}