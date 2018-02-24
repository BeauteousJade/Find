package com.example.pby.find.bean.recyclerView;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pby on 2018/2/1.
 */

public class AreaBean implements Parcelable{
    private String name = null;
    private int iconId = 0;

    public AreaBean(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }

    protected AreaBean(Parcel in) {
        name = in.readString();
        iconId = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }


    public static final Creator<AreaBean> CREATOR = new Creator<AreaBean>() {
        @Override
        public AreaBean createFromParcel(Parcel in) {
            return new AreaBean(in);
        }

        @Override
        public AreaBean[] newArray(int size) {
            return new AreaBean[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(iconId);
    }
}
