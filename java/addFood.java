package com.example.foodorderingsystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class addFood extends AppCompatActivity {

    EditText foodName, foodDesc;
    DatabaseReference reference;
    private StorageReference storageReference;
    ImageView foodPicture;
    String imageUrl;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        ImageView backBtn = (ImageView) findViewById(R.id.back_button); //onClick usage for back button (line 26-31)
        ImageView menuBtn = (ImageView) findViewById(R.id.menu_button); //onClick usage for menu button (line 33-38)
        TextView pageTitle = (TextView) findViewById(R.id.page_title); //sets page header (line 40)

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addFood.this, crudMenu.class);
                startActivity(intent);
            }
        });

        foodName = findViewById(R.id.food_name);
        foodDesc = findViewById(R.id.food_desc);
        foodPicture = findViewById(R.id.food_picture);
        reference = FirebaseDatabase.getInstance().getReference().child("menu");

        Button confirmAdd = (Button) findViewById(R.id.confirmAdd);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            foodPicture.setImageURI(uri);
                        }else {
                            Toast.makeText(addFood.this, "No Image", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        foodPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        confirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    public void saveData(){

        storageReference = FirebaseStorage.getInstance().getReference().child("uploadsMenu")
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
        String foodname = foodName.getText().toString();
        String fooddesc = foodDesc.getText().toString();

        AddFoodHelperClass addFoodHelperClass = new AddFoodHelperClass(foodname, fooddesc, imageUrl);

        FirebaseDatabase.getInstance().getReference("menu").child(foodname)
                .setValue(addFoodHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(addFood.this, "Saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (addFood.this, crudMenu.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addFood.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
