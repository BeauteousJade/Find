package com.example.pby.find.bean.recyclerView;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pby on 2018/2/2.
 */

public class NoteBean implements Parcelable{
    private String id = null;
    private String email = null;
    private String head = null;
    private String name = null;
    private String time = null;
    private String content = null;
    private String areaName = null;
    private List<String> images = null;
    private int appreciateNumber = 0;
    private int isAppreciate = 0;
    private int readNumber = 0;
    private int discussNumber = 0;

    public NoteBean(){

    }
    protected NoteBean(Parcel in) {
        id = in.readString();
        email = in.readString();
        head = in.readString();
        name = in.readString();
        time = in.readString();
        content = in.readString();
        areaName = in.readString();
        images = in.createStringArrayList();
        appreciateNumber = in.readInt();
        isAppreciate = in.readInt();
        readNumber = in.readInt();
        discussNumber = in.readInt();
    }

    public static final Creator<NoteBean> CREATOR = new Creator<NoteBean>() {
        @Override
        public NoteBean createFromParcel(Parcel in) {
            return new NoteBean(in);
        }

        @Override
        public NoteBean[] newArray(int size) {
            return new NoteBean[size];
        }
    };

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(int readNumber) {
        this.readNumber = readNumber;
    }

    public int getDiscussNumber() {
        return discussNumber;
    }

    public void setDiscussNumber(int discussNumber) {
        this.discussNumber = discussNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAppreciateNumber() {
        return appreciateNumber;
    }

    public void setAppreciateNumber(int appreciateNumber) {
        this.appreciateNumber = appreciateNumber;
    }

    public int getIsAppreciate() {
        return isAppreciate;
    }

    public void setIsAppreciate(int isAppreciate) {
        this.isAppreciate = isAppreciate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(head);
        dest.writeString(name);
        dest.writeString(time);
        dest.writeString(content);
        dest.writeString(areaName);
        dest.writeStringList(images);
        dest.writeInt(appreciateNumber);
        dest.writeInt(isAppreciate);
        dest.writeInt(readNumber);
        dest.writeInt(discussNumber);
    }
}
