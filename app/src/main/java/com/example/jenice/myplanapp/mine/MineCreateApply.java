package com.example.jenice.myplanapp.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;

public class MineCreateApply extends AppCompatActivity implements View.OnClickListener{

    private Button btnOk;
    private ImageView ivBack,ivPhoto,ivView;
    private EditText etTitle,etIntro;
    private AVUser user = AVUser.getCurrentUser();
    private String title,intro;
    private Boolean isUpload = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_create_apply);

        btnOk = (Button) findViewById(R.id.btn_mine_create_apply_ok);
        ivBack = (ImageView) findViewById(R.id.iv_mine_create_apply_back);
        ivPhoto = (ImageView) findViewById(R.id.iv_mine_create_photo);
        etTitle = (EditText) findViewById(R.id.et_mine_create_apply_name);
        etIntro = (EditText) findViewById(R.id.et_mine_create_apply_dec);
        ivView = (ImageView) findViewById(R.id.iv_mine_create_apply_view);

        ivView.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //确认创建按钮，需要判断内容都填写完整没有
            case R.id.btn_mine_create_apply_ok:
                title = etTitle.getText().toString();
                intro = etIntro.getText().toString();
                //没有判断是否上传了照片！！！！！！！！！！！！！
                if (title.length()==0){
                    Toast.makeText(getApplicationContext(), "请填写计划标题！", Toast.LENGTH_SHORT).show();
                }else if (intro.length()==0){
                    Toast.makeText(getApplicationContext(), "请填写计划简介！", Toast.LENGTH_SHORT).show();
                }/*else if(isUpload == false){
                    Toast.makeText(getApplicationContext(),"请上传计划图片！",Toast.LENGTH_SHORT).show();
                }*/else {
                    //将计划保存在Plan中，并将图片传上去
                    AVObject upload = new AVObject("Plan");
                    upload.put("PlanDeclaration",intro);
                    upload.put("UserName",user.getUsername());
                    upload.put("PlanName",title);
                    upload.saveInBackground();

                    //上传图片没实现！！！！！！！！！！！！！！！

                    startActivity(new Intent(MineCreateApply.this,MineCreateApplyNext.class).putExtra("name",title));
                }
                break;
            //返回上个界面
            case R.id.iv_mine_create_apply_back:
                Intent intent_back = new Intent(MineCreateApply.this, MainActivity.class);
                intent_back.putExtra("main",3);
                startActivity(intent_back);
                break;
            //计划背景图,点击后会打开相册
            case R.id.iv_mine_create_photo:
                //如果上传成功，那么置isUpload为true

                break;
            case R.id.iv_mine_create_apply_view:
                startActivity(new Intent(MineCreateApply.this,MineCreate.class));
                break;
        }
    }

}
