package com.example.pby.find.bean.recyclerView;

import java.util.List;

/**
 * Created by pby on 2018/2/13.
 */

public class MessageDiscussBean {
    private String email = null;
    private String head = null;
    private String name = null;
    private String time = null;

    private String noteContent = null;
    private List<String> noteImages = null;
    private String discussContent = null;
    private List<String> discussImages = null;
    private String noteId = null;
    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

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

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public List<String> getNoteImages() {
        return noteImages;
    }

    public void setNoteImages(List<String> noteImages) {
        this.noteImages = noteImages;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }

    public List<String> getDiscussImages() {
        return discussImages;
    }

    public void setDiscussImages(List<String> discussImages) {
        this.discussImages = discussImages;
    }

}
