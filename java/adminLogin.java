package com.example.foodorderingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class adminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        TextView pageTitle = (TextView) findViewById(R.id.page_title);

        pageTitle.setText("Admin Login");

        ImageView backBtn = (ImageView) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminLogin.this, adminLock.class);
                startActivity(intent);
            }
        });
    }
    public void goRegister(View view){
        Intent intent = new Intent (adminLogin.this, adminRegister.class);
        Button btnRegisterAdmin = (Button) findViewById(R.id.btn_admin_register);
        startActivity(intent);
    }
}