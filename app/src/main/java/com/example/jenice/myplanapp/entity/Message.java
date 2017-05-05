package com.example.jenice.myplanapp.entity;

import com.avos.avoscloud.AVObject;

/**
 * Created by Administrator on 2017/5/5.
 */

public class Message extends AVObject {
    private String Name;
    private String Content;
    private String Type;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
