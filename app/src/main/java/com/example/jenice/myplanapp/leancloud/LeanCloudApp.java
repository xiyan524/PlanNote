package com.example.jenice.myplanapp.leancloud;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by Jenice on 2017/4/17.
 */

public class LeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"vwJ55OwbsBpi4V7QuxC6VXoU-gzGzoHsz","r5SKfTrntn2HkibK1LmawYTo");
    }
}
