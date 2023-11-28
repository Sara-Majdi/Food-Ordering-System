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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darpafoodordering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class register extends AppCompatActivity {

    private Button btnRegister;
    private EditText name, email, phone, password;
    private TextView ToLogIn;
    private ProgressDialog progressBar;

    boolean allFieldsChecked = false;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        ToLogIn = (TextView) findViewById(R.id.ToLogIn);
        progressBar = new ProgressDialog(this);

        // page title
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Register");

        drawerLayout = findViewById(R.id.drawer);
        ImageView menu_button = (ImageView) findViewById(R.id.menu_button);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // allFieldsChecked = CheckFields();
                //    if(allFieldsChecked){
                        CreateAccount();
                 //   }
            }
        });
    }
/*
    private boolean CheckFields(){

        if(name.length() == 0){
            name.setError("Field is required!");
            return false;
        }else if(name.length() <= 2){
            name.setError("Input too short!");
            return false;
        }else if(name.length() >= 15){
            name.setError("Input too long!");
            return false;
        }

        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$";

        if(email.length() == 0){
            email.setError("Field is required!");
            return false;
        }else if(!email.getText().toString().matches(emailPattern)){
            email.setError("Invalid Email!");
            return false;
        }

        if(password.length() == 0){
            password.setError("Field is required!");
            return false;
        }else if(password.length() <= 7){
            password.setError("Password too short!");
            return false;
        }


        CreateAccount();
        return true;
    }

 */

        private void CreateAccount(){

            String Name = name.getText().toString();
            String Email = email.getText().toString();
            String Phone = phone.getText().toString();
            String Password = password.getText().toString();

            if (TextUtils.isEmpty(Name)) {
                Toast.makeText(this, "Please fill in your name", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Email)) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Phone)) {
                Toast.makeText(this, "Please fill in your phone number", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Password)) {
                Toast.makeText(this, "Please fill in your password", Toast.LENGTH_SHORT).show();
            }
            else {
                progressBar.setTitle("Creating Account");
                progressBar.setMessage("Please wait...");
                progressBar.setCanceledOnTouchOutside(false);
                progressBar.show();

                ValidatePhoneNumber(Name, Email, Phone, Password);
           }
        }
    /* SARA COMMENTED
            private boolean isValidEmail(String email) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";
                return email.matches(emailPattern);
            }



            ToLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    */
    private void ValidatePhoneNumber(String Name, String Email, String Phone, String Password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot)
            {
                //if the phone number does not exist in database yet, can create account
                if (!(datasnapshot.child("Users").child(Phone).exists()))
                {
                    HashMap<String, Object> usersdataMap = new HashMap<>();
                    usersdataMap.put("name", Name);
                    usersdataMap.put("email", Email);
                    usersdataMap.put("phone", Phone);
                    usersdataMap.put("password", Password);

                    RootRef.child("Users").child(Phone).updateChildren(usersdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(register.this,"Your Account Has Been Created", Toast.LENGTH_SHORT).show();
                                        progressBar.dismiss();

                                        Intent intent = new Intent(register.this, login.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        progressBar.dismiss();
                                        Toast.makeText(register.this, "Error, Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(register.this,"This " + phone + " already exist.", Toast.LENGTH_SHORT).show();
                    progressBar.dismiss();
                    Toast.makeText(register.this, "Please try again using another phone number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

/* SARA COMMENTED
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


    // when user click Register in the navigation menu, it we recreate the same Register Page
    public void ClickRegister(View view) {recreate();}

    // when user click Home in the navigation menu, it will direct user to Home Page from Register Page
    public void ClickHome(View view) { redirectActivity(this, MainActivity.class);}

    // when user click Login in the navigation menu, it will direct user to Login Page from Register Page
    public void ClickLogin(View view) { redirectActivity(this, login.class); }




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
 */
    // user press "Already Have An Account", it will direct user to Login Page
    public void clickToLogIn (View view) {
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);
    }
}