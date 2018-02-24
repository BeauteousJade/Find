package com.example.pby.find.bean.recyclerView;

/**
 * Created by pby on 2018/2/18.
 */

public class MessageFollowBean {
    private String email = null;
    private String head = null;
    private String name = null;
    private String time = null;
    private int isFollow = 0;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }
}
