package com.example.net.net.bean;

import com.example.net.net.bean.base.BaseBean1;

import java.util.List;

/**
 * Created by pby on 2018/2/8.
 */

public class Note{
    private String id = null;
    private String email = null;
    private String content = null;
    private List<String> images = null;
    private List<String> appreciateUserList = null;
    private String areaName = null;
    private String time = null;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getAppreciateUserList() {
        return appreciateUserList;
    }

    public void setAppreciateUserList(List<String> appreciateUserList) {
        this.appreciateUserList = appreciateUserList;
    }



    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
