package com.example.foodorderingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class adminRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        TextView pageTitle = (TextView) findViewById(R.id.page_title);

        pageTitle.setText("Admin Register");

        ImageView backBtn = (ImageView) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminRegister.this, adminLock.class);
                startActivity(intent);
            }
        });
    }
    public void goLogin(View view){
        Intent intent = new Intent (adminRegister.this, adminLogin.class);
        Button btnLoginAdmin = (Button) findViewById(R.id.btn_admin_login);
        startActivity(intent);
    }
}