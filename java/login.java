package com.example.darpafoodordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        drawerLayout = findViewById(R.id.drawer);
        ImageView menu_button = (ImageView) findViewById(R.id.menu_button);


        // page title
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Log In");


        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final Button btnLogIn = findViewById(R.id.btnLogIn);
        final TextView btnRegisterNow = findViewById(R.id.btnRegisterNow);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String textEmailAddress = email.getText().toString();
                final String textPassword = password.getText().toString();

                if (textEmailAddress.isEmpty() || textPassword.isEmpty()) {
                    // error message if user has not entered email or password
                    Toast.makeText(login.this, "Please Enter Your Email Address Or Password",
                            Toast.LENGTH_SHORT).show();
                } else {


                }
            }

        });

        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Open Register Activity
                startActivity(new Intent(login.this, register.class));
            }
        });
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

    // when user click Login in the navigation menu, it we recreate the same Login Page
    public void ClickLogin(View view) {
        recreate();
    }

    // when user click Home in the navigation menu, it will direct user to Home Page from Login Page
    public void ClickHome(View view) {
        redirectActivity(this, MainActivity.class);
    }

    // when user click Register in the navigation menu, it will direct user to Home Page from Login Page
    public void ClickRegister(View view) {
        redirectActivity(this, register.class);
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
}
