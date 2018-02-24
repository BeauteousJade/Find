package com.example.pby.find.bean.recyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by pby on 2018/2/20.
 */

public class MineItemBean implements MultiItemEntity {

    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_EMPTY = 2;
    public static final int TYPE_MODE = 3;
    private int itemType;
    private int iconId = 0;
    private String title = null;
    private int count = 0;

    public MineItemBean(int itemType) {
        this.itemType = itemType;
    }

    public MineItemBean(int iconId, String title, int count) {
        this.iconId = iconId;
        this.title = title;
        this.count = count;
        this.itemType = TYPE_NORMAL;
    }
    public MineItemBean(int iconId, String title){
        this.iconId = iconId;
        this.title = title;
        this.itemType = TYPE_MODE;
    }
    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
