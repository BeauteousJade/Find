package com.example.net.net.bean;

import java.util.List;

/**
 * Created by pby on 2018/2/9.
 */

public class Discuss {
    private String id = null;
    private String email = null;
    private String content = null;
    private int isChild = 0;
    private String parentEmail = null;
    private String parentCOntent = null;
    private String time = null;
    private String noteId = null;
    private List<String> images = null;
    private int isRead = 0;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getIsChild() {
        return isChild;
    }

    public void setIsChild(int isChild) {
        this.isChild = isChild;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getParentCOntent() {
        return parentCOntent;
    }

    public void setParentCOntent(String parentCOntent) {
        this.parentCOntent = parentCOntent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
