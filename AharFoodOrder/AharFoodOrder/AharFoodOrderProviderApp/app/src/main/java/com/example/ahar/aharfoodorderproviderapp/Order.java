package com.example.ahar.aharfoodorderproviderapp;

/**
 * Created by Chandan on 3/19/2018.
 */

public class Order {


//write code

    String username,phone,address,itemname;

    public Order(){

    }

    public Order(String username,String phone,String address,String itemname){
        this.username=username;
        this.phone=phone;
        this.address=address;
        this.itemname=itemname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    //

}
