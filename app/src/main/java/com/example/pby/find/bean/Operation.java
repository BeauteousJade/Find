package com.example.pby.find.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pby on 2018/2/9.
 */

public class Operation implements Parcelable{
    private String id = null;
    private boolean isChild = false;
    private String name = null;
    private String parentEmail = null;
    private String parentContent = null;
    public Operation(){

    }
    protected Operation(Parcel in) {
        id = in.readString();
        isChild = in.readByte() != 0;
        name = in.readString();
        parentEmail = in.readString();
        parentContent = in.readString();
    }

    public static final Creator<Operation> CREATOR = new Creator<Operation>() {
        @Override
        public Operation createFromParcel(Parcel in) {
            return new Operation(in);
        }

        @Override
        public Operation[] newArray(int size) {
            return new Operation[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getParentContent() {
        return parentContent;
    }

    public void setParentContent(String parentContent) {
        this.parentContent = parentContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeByte((byte) (isChild ? 1 : 0));
        dest.writeString(name);
        dest.writeString(parentEmail);
        dest.writeString(parentContent);
    }
}
