package com.example.ahar.aharfoodorderclientapp;

/**
 * Created by Chandan on 3/19/2018.
 */

public class Food {

    //write code
    private String name,price,image,desc;

    public Food(){

    }

    public Food(String name,String price, String image, String desc){
        this.name=name;
        this.desc=desc;
        this.price=price;
        this.image=image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    //

}
