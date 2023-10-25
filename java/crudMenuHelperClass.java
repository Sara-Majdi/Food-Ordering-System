package com.example.foodorderingsystem;

public class crudMenuHelperClass {

    String food_name;
    String food_pic;
    String food_desc;

    public crudMenuHelperClass(String food_name, String food_pic, String food_desc) {
        this.food_name = food_name;
        this.food_pic = food_pic;
        this.food_desc = food_desc;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_pic() {
        return food_pic;
    }

    public void setFood_pic(String food_pic) {
        this.food_pic = food_pic;
    }

    public String getFood_desc() {
        return food_desc;
    }

    public void setFood_desc(String food_desc) {
        this.food_desc = food_desc;
    }

    crudMenuHelperClass(){

    }

}
