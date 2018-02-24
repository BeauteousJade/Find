package com.example.pby.find.bean.recyclerView;

/**
 * Created by pby on 2018/2/12.
 */

public class MessageBean {
    private int iconId = 0;
    private String text = null;
    private int count = 0;

    public MessageBean(int iconId, String text, int count) {
        this.iconId = iconId;
        this.text = text;
        this.count = count;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
