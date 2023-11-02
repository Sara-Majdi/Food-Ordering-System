package com.example.darpafoodordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // page title
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Homepage");

        drawerLayout = findViewById(R.id.drawer);
    }


    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }


    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    // when user click home in the navigation menu, it we recreate the same page
    public void ClickHome(View view) {
        recreate();
    }


    // when user click Register in the navigation menu, it will direct user to Register page
    public void ClickRegister(View view) { redirectActivity(this, register.class);}


    // when user click Login in the navigation menu, it will direct user to Login page
    public void ClickLogin(View view) {
        redirectActivity(this, login.class);
    }

    public static void redirectActivity(Activity activity, Class Class) {
        Intent intent = new Intent(activity, Class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);


    }
    // when user press Register button on the home page, will bring user to the Register page
    public void register(View view) {
        Intent intent = new Intent (this, register.class);
        startActivity(intent);
    }

    // when user press Login button on the home page, will bring user to the Login page
    public void login(View view) {
        Intent intent = new Intent (this, login.class);
        startActivity(intent);
    }
}



