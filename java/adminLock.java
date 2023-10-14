package com.example.foodorderingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class adminLock extends AppCompatActivity {

    EditText adminLock;
    Button btnTryPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lock);

        TextView pageTitle = (TextView) findViewById(R.id.page_title);

        ImageView backBtn = (ImageView) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.foodorderingsystem.adminLock.this, com.example.foodorderingsystem.adminLock.class);
                startActivity(intent);
            }
        });

        pageTitle.setText("DARPA LOCK");

        adminLock = findViewById(R.id.admin_lock);
        btnTryPass = findViewById(R.id.admin_lock_password);
        btnTryPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = adminLock.getText().toString();

                if(pwd.equals("DARPA")){
                    Intent intent = new Intent(adminLock.this, adminLogin.class);
                    Button goLoginPage = (Button) findViewById(R.id.admin_lock_password);
                    startActivity(intent);
                }else{
                    Toast.makeText(com.example.foodorderingsystem.adminLock.this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}