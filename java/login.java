package com.example.darpafoodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // page title
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Log In");

        final EditText email =  findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final Button btnLogIn = findViewById(R.id.btnLogIn);
        final TextView btnRegisterNow = findViewById(R.id.btnRegisterNow);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String textEmailAddress = email.getText().toString();
                final String textPassword = password.getText().toString();

                if(textEmailAddress.isEmpty() || textPassword.isEmpty()) {
                    // error message if user has not entered email or password
                    Toast.makeText(login.this, "Please Enter Your Email Address Or Password",
                            Toast.LENGTH_SHORT).show();
                }
                else {



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
}
