package com.example.darpafoodordering;

import androidx.annotation.NonNull;
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

import com.example.darpafoodordering.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {


    // create object of DatabaseReference class to access Realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://register-and-login-54989-default-rtdb.firebaseio.com/");
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // page title
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Register");

        drawerLayout = findViewById(R.id.drawer);
        ImageView menu_button = (ImageView) findViewById(R.id.menu_button);


        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);

        final Button btnRegister = findViewById(R.id.btnRegister);
        final TextView ToLogIn = findViewById(R.id.ToLogIn);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get data from edit texts into string variables
                final String textPersonName = name.getText().toString();
                final String textEmailAddress = email.getText().toString();
                final String textPhonetic = phone.getText().toString();
                final String textPassword = password.getText().toString();

                // check if user fill all the fields before sending data to database
                if(textPersonName.isEmpty() || textEmailAddress.isEmpty() || textPhonetic.isEmpty() || textPassword.isEmpty() ) {
                    Toast.makeText(register.this, "Please Fill Up All Fields", Toast.LENGTH_SHORT).show();
                }

                else {


                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            // check if email not registered before
                            if(snapshot.hasChild(textPhonetic)) {
                                Toast.makeText(register.this, "Phone Number is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                // sending data to firebase realtime database
                                // using email address different identity of every user
                                databaseReference.child("users").child(textPhonetic).child("name").setValue(textPersonName);
                                databaseReference.child("users").child(textPhonetic).child("email").setValue(textEmailAddress);
                                databaseReference.child("users").child(textPhonetic).child("password").setValue(textPassword);

                                // shows a success message and finish the activity
                                Toast.makeText(register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(register.this, login.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
        ToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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


    // inside drawer page press home buttton will bring user to homepage
    public void ClickHome(View view) {
        redirectActivity(this, login.class);
    }

    public void ClickShop(View view) {
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
}
