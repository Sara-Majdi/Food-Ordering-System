package com.example.foodorderingsystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class adminRegister extends AppCompatActivity {

    EditText adminFirstName, adminLastName, adminEmail, adminPassword, adminPoints;
    DatabaseReference reference;
    private StorageReference storageReference;
    ImageView adminPicture;
    String imageUrl;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        ImageView backBtn = (ImageView) findViewById(R.id.back_button); //onClick usage for back button (line 26-31)
        ImageView menuBtn = (ImageView) findViewById(R.id.menu_button); //onClick usage for menu button (line 33-38)
        TextView pageTitle = (TextView) findViewById(R.id.page_title); //sets page header (line 40)

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminRegister.this, adminLogin.class);
                startActivity(intent);
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(adminRegister.this, "Soon come", Toast.LENGTH_SHORT).show();
            }
        });

        pageTitle.setText("Register");

        adminFirstName = findViewById(R.id.admin_first_name);
        adminLastName = findViewById(R.id.admin_last_name);
        adminEmail = findViewById(R.id.admin_email);
        adminPassword = findViewById(R.id.admin_password);
        adminPoints = findViewById(R.id.admin_points);
        adminPicture = findViewById(R.id.admin_picture);
        reference = FirebaseDatabase.getInstance().getReference().child("admin");

        Button btnRegister = (Button) findViewById(R.id.btn_admin_register);
        Button btnLogin = (Button) findViewById(R.id.btn_admin_login);



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            adminPicture.setImageURI(uri);
                        }else {
                            Toast.makeText(adminRegister.this, "No Image", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        adminPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminRegister.this, adminLogin.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){

        storageReference = FirebaseStorage.getInstance().getReference().child("uploads")
                .child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
    public void uploadData() {
        String fname = adminFirstName.getText().toString();
        String lname = adminLastName.getText().toString();
        String email = adminEmail.getText().toString();
        String password = adminPassword.getText().toString();
        String points = adminPoints.getText().toString();

        AdminRegisterHelperClass adminRegisterHelperClass = new AdminRegisterHelperClass(fname, lname, email, password, points, imageUrl);

        FirebaseDatabase.getInstance().getReference("admin").child(fname)
                .setValue(adminRegisterHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(adminRegister.this, "Saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (adminRegister.this, adminLogin.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(adminRegister.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}