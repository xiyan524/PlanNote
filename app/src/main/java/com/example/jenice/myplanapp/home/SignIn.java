package com.example.jenice.myplanapp.home;

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
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.example.jenice.myplanapp.R;
import com.example.jenice.myplanapp.fragment.MainActivity;

import java.io.FileNotFoundException;

public class SignIn extends AppCompatActivity {
    EditText edt_content;
    int plan_day;
    String user_name;
    String plan_name;

    //调用系统相册-选择图片
    private static final int IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Bundle bundle = this.getIntent().getExtras();
        plan_day=bundle.getInt("Day");
        user_name=bundle.getString("UserName");
        plan_name=bundle.getString("PlanName");

        edt_content = (EditText) findViewById(R.id.sign_content);
    }

    public void SignInFinal(View view){
        String content = edt_content.getText().toString();
        //插入一条打卡数据
        AVObject join_plan = new AVObject("UserCard");
        join_plan.put("UserName", user_name);
        join_plan.put("UserInfo", content);
        join_plan.put("PlanName",plan_name);
        join_plan.put("PlanDay",plan_day);
        join_plan.saveInBackground();
        Toast.makeText(this,"打卡成功",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SignIn.this, MainActivity.class);
        startActivity(intent);
    }

    public void Choose_Img(View view){
        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
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
                String file_name = user_name+"_"+plan_name+"_"+plan_day;
                AVFile file = AVFile.withAbsoluteLocalPath(file_name, imagePath);
                file.saveInBackground();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            c.close();
        }
    }

}
