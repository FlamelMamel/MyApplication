package com.example.myapplication.model;

public class Favorites {
    private String name;
    private String address;
    private int saunaImg;

    public Favorites(String name, String address, int saunaImg){

        this.name=name;
        this.address=address;
        this.saunaImg=saunaImg;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String capital) {
        this.address = address;
    }

    public int getSaunaImg() {
        return this.saunaImg;
    }

    public void setSaunaImg(int saunaImg) {
        this.saunaImg = saunaImg;
    }
}
