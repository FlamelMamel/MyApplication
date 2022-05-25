package com.example.myapplication.model;

public class Course {

    int id, category, all;
    String img;

    public Course(int id, String img, int category, int all) {
        this.id = id;
        this.img = img;
        this.category = category;
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
