package com.example.pby.find.bean.recyclerView;

/**
 * Created by pby on 2018/2/7.
 */

public class ImageBean {


    private String imagePath = null;
    private Type imageType = null;

    public enum Type {
        expression, image, head
    }

    public Type getImageType() {
        return imageType;
    }

    public void setImageType(Type imageType) {
        this.imageType = imageType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
