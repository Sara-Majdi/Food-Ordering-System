package com.example.darpafoodordering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class login extends AppCompatActivity {

    private Button btnLogIn;
    private EditText phone, password;
    private TextView btnRegisterNow;
    private ProgressDialog progressBar;
    private CheckBox checkBoxRememberMe;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        drawerLayout = findViewById(R.id.drawer);
        ImageView menu_button = (ImageView) findViewById(R.id.menu_button);


        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        btnRegisterNow = (TextView) findViewById(R.id.btnRegisterNow);
        progressBar = new ProgressDialog(this);

        checkBoxRememberMe = (CheckBox) findViewById(R.id.rememberMeCheckBox);
        Paper.init(this);

        // page title
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Log In");

/* SARA COMMENTED
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final Button btnLogIn = findViewById(R.id.btnLogIn);
        final TextView btnRegisterNow = findViewById(R.id.btnRegisterNow);
*/
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });


        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });

    }

    private void LoginUser() {

        String Phone = phone.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Phone)) {
            Toast.makeText(this, "Please fill in your phone", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please fill in your password", Toast.LENGTH_SHORT).show();
        }
        else {
            progressBar.setTitle("Login Account");
            progressBar.setMessage("Please wait...");
            progressBar.setCanceledOnTouchOutside(false);
            progressBar.show();

            AllowAccessToAccount(Phone, Password);

        }
    }

    private void AllowAccessToAccount(String phone, String password) {

        //checkbox
        if(checkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("Users").child(phone).exists()) {
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    //if getPhone equals to the phone number user inputs
                    if (usersData.getPhone().equals(phone))
                    {

                        // if password from db same as user inputs
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(login.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                            progressBar.dismiss();

                            Intent intent = new Intent(login.this, MenuCategoryActivity.class);
                            startActivity(intent);


                        }
                        else {
                            progressBar.dismiss();
                            Toast.makeText(login.this, "Incorrect Password!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(login.this, "Account Using This " + phone +" Does Not Exist", Toast.LENGTH_SHORT).show();
                    progressBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

        /* SARA COMMENTED
        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Open Register Activity
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
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

         */
