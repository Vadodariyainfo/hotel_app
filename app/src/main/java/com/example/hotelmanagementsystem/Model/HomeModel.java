package com.example.hotelmanagementsystem.Model;

public class HomeModel {

//    int circle;
    String id;
    int image;
    String name;

    public HomeModel(String id, int image, String name) {
//        this.circle = circle;
        this.id = id;
        this.image = image;
        this.name = name;
    }

//    public int getCircle() {
//        return circle;
//    }
//
//    public void setCircle(int circle) {
//        this.circle = circle;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {

        return image;
    }

    public void setImage(int image) {

        this.image = image;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
