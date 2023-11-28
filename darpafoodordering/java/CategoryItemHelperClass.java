package com.example.darpafoodordering;

public class CategoryItemHelperClass {
    String name;
    String price;
    String image;
    String category;

    public CategoryItemHelperClass(String name, String price, String image, String category) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    // Getters and setters
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
}
