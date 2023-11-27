package com.example.darpafoodordering;

import android.util.Log;

public class Prevalent
{
    public static Users currentOnlineUser;

    public static final String UserPhoneKey = "UserPhone";
    public static final String UserPasswordKey = "UserPassword";

    //Method to set current online user
    public static void setCurrentOnlineUser(Users user) {
        currentOnlineUser = user;

        // After setting the current user in Prevalent
        Prevalent.setCurrentOnlineUser(currentOnlineUser);
        Log.d("UserCheck", "Current User: " + Prevalent.currentOnlineUser);
    }

    //Method to get current online user's phone number
    public static String getCurrentUserPhone() {
        if (currentOnlineUser != null) {

            String userPhone = Prevalent.getCurrentUserPhone();
            Log.d("UserCheck", "User Phone: " + userPhone);
            return userPhone;
            //return currentOnlineUser.getPhone();
        }
        return null;
    }
}
