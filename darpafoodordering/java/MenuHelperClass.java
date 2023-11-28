package com.example.darpafoodordering;

public class MenuHelperClass {
    String name;
    String price;
    String image;
    String category;
    String productId;

    public MenuHelperClass(String name, String price, String image, String Category) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    MenuHelperClass(){

    }
}
