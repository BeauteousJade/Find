package com.example.pby.find.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by pby on 2018/2/22.
 */

public class SettingBean implements MultiItemEntity {
    private String title = null;
    private String content = null;

    private int type = 0;

    public static final int TYPE_IMG = 1;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_TITLE = 3;
    public static final int TYPE_SWITCH = 4;

    public SettingBean(String title, String content, int type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }
}
