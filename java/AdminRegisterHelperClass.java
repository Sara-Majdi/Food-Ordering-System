package com.example.foodorderingsystem;

public class AdminRegisterHelperClass {

    String first_name;
    String last_name;
    String email;
    String password;
    String points;
    String user_picture;

    public String getPoints() {
        return points;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUser_picture() {
        return user_picture;
    }


    public AdminRegisterHelperClass(String first_name, String last_name, String email, String password, String points, String user_picture) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.points = points;
        this.user_picture = user_picture;
    }
    public AdminRegisterHelperClass(){

    }
}
