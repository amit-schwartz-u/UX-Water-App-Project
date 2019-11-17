package com.example.wateryourself;

public class Model {
    private int image;
    private String title;
    private String desc;

    public Model(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.desc = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
