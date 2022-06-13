package com.example.myapplication.model;

public class Favorites {
    private String name;
    private String address;
    private int saunaImg;
    private String price;
    private String description;



    public Favorites(String name, String address, int saunaImg, String price, String description) {

        this.name = name;
        this.address = address;
        this.saunaImg = saunaImg;
        this.price = price;
        this.description = description;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}