package com.example.jenice.myplanapp.mine;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;
import com.example.jenice.myplanapp.task.updatePlanImgTask;

import java.io.FileNotFoundException;

public class MinePersonal extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack,ivAccountGo,ivphoto;
    private LinearLayout llPhoto;
    private EditText etGoal,etIntro;
    private TextView tvEdit,tvName,tvSex,tvGoal,tvIntro;
    private Spinner spnSex;
    private String gender,goal,intro;

    private AVUser user = AVUser.getCurrentUser();
    //调用系统相册-选择图片
    private static final int IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_personal);

        ivBack = (ImageView) findViewById(R.id.iv_mine_personal_back);
        ivAccountGo = (ImageView) findViewById(R.id.iv_mine_personal_account);
        ivphoto = (ImageView) findViewById(R.id.iv_mine_personal_photo);
        llPhoto = (LinearLayout) findViewById(R.id.ll_mine_personal_photo);
        etGoal = (EditText) findViewById(R.id.et_mine_personal_goal_edit);
        etIntro = (EditText) findViewById(R.id.et_mine_personal_intro_edit);
        tvEdit = (TextView) findViewById(R.id.tv_mine_personal_edit);
        tvName = (TextView) findViewById(R.id.tv_mine_personal_name_show);
        tvSex = (TextView) findViewById(R.id.tv_mine_personal_sex_show);
        tvGoal = (TextView) findViewById(R.id.tv_mine_personal_goal_show);
        tvIntro = (TextView) findViewById(R.id.tv_mine_personal_intro_show);
        spnSex = (Spinner) findViewById(R.id.spn_mine_personal_sex_edit);

        ivBack.setOnClickListener(this);
        ivAccountGo.setOnClickListener(this);
        llPhoto.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
        spnSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = view.getResources().getStringArray(R.array.mine_personal_sex)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = "保密";
            }
        });

        tvName.setText(user.getUsername());
        showInfo();

        //加载用户的头像
        String cql1 = "select url from _File where name=?";
        AVQuery.doCloudQueryInBackground(cql1, new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                //显示用户头像中最新的一张，后台管理员删除旧图像
                String url =  avCloudQueryResult.getResults().get(avCloudQueryResult.getResults().size()-1).getString("url");
                //开启异步线程加载图片
                new updatePlanImgTask(ivphoto).execute(url,user.getUsername());
            }
        }, user.getUsername());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //返回按钮
            case R.id.iv_mine_personal_back:
                Intent intent_back = new Intent(MinePersonal.this, MainActivity.class);
                intent_back.putExtra("main",3);
                finish();
                startActivity(intent_back);
                break;
            //跳转到绑定账号界面
            case R.id.iv_mine_personal_account:
                Intent intent_account = new Intent(MinePersonal.this,MineAccount.class);
                finish();
                startActivity(intent_account);
                break;
            //修改头像
            case R.id.ll_mine_personal_photo:
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
                break;
            //编辑资料,当点击编辑会变为完成，点击完成弹出对话框，确认后修改信息
            case R.id.tv_mine_personal_edit:
                if (tvEdit.getText().equals("编辑")){
                    changeToEdit();
                }else if(tvEdit.getText().equals("完成")){
                    modifyPersonalInfo();
                    changeToDone();
                }
                break;
        }
    }

    //相册图片选择之后的响应函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            final Bitmap bmp= BitmapFactory.decodeFile(imagePath);
            //上传图片
            try {
                //自己定义一个文件名字
                String file_name = user.getUsername();
                AVFile file = AVFile.withAbsoluteLocalPath(file_name, imagePath);
                file.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        ivphoto.setImageBitmap(bmp);
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            c.close();
        }
    }

    //将界面变为编辑界面状态
    private void changeToEdit()
    {
        //性别
        tvSex.setVisibility(View.INVISIBLE);
        spnSex.setVisibility(View.VISIBLE);
        //目标
        tvGoal.setVisibility(View.INVISIBLE);
        etGoal.setVisibility(View.VISIBLE);
        //介绍
        tvIntro.setVisibility(View.INVISIBLE);
        etIntro.setVisibility(View.VISIBLE);
        //将编辑改为完成
        tvEdit.setText("完成");
    }

    //将界面变为完成界面状态
    private void changeToDone()
    {
        tvSex.setText(gender);
        tvGoal.setText(goal);
        tvIntro.setText(intro);
        //性别
        tvSex.setVisibility(View.VISIBLE);
        spnSex.setVisibility(View.INVISIBLE);
        //目标
        tvGoal.setVisibility(View.VISIBLE);
        etGoal.setVisibility(View.INVISIBLE);
        //介绍
        tvIntro.setVisibility(View.VISIBLE);
        etIntro.setVisibility(View.INVISIBLE);
        //将编辑改为完成
        tvEdit.setText("编辑");
    }

    //修改个人信息
    private void modifyPersonalInfo(){
        goal = etGoal.getText().toString();
        intro = etIntro.getText().toString();
        AVObject update = AVObject.createWithoutData("_User",user.getObjectId());
        update.put("gender",gender);
        update.put("goal",goal);
        update.put("introduction",intro);
        update.saveInBackground();
        //一定要刷新，才能保证拿到最新的数据
        update.fetchInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                gender = avObject.getString("gender");
                goal = avObject.getString("goal");
                intro = avObject.getString("introduction");
            }
        });
    }

    //显示用户信息
    private void showInfo(){
        tvSex.setText(user.getString("gender"));
        tvIntro.setText(user.getString("introduction"));
        tvGoal.setText(user.getString("goal"));
        //头像的设置没有完成!!!!!!!!!!!!!!!!!!!!!
    }
}


