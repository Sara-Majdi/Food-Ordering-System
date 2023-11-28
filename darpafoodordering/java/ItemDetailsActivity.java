package com.example.darpafoodordering;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class ItemDetailsActivity extends AppCompatActivity {


    private ImageView itemImage;
    private TextView itemPrice, itemName, itemQuantity;
    private Button addButton, minusButton, addToCartButton;
    private EditText phone;
    private String productRandomKey;


    private DatabaseReference itemReference;
    private DatabaseReference userReference;


    private int quantity = 0;
    private String productName = "";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        productName = getIntent().getStringExtra("name");

        //Initialize
        itemImage = findViewById(R.id.item_image);
        itemName = findViewById(R.id.item_name);
        itemPrice = findViewById(R.id.item_price);
        itemQuantity = findViewById(R.id.item_Quantity);
        addButton = findViewById(R.id.add_button);
        minusButton = findViewById(R.id.minus_button);
        addToCartButton = findViewById(R.id.addToCart_button);
        phone = findViewById(R.id.phone);

        getProductDetails(productName);


        //Retrieve item name from intent
        String itemNameFromIntent = getIntent().getStringExtra("name");

        itemReference = FirebaseDatabase.getInstance().getReference().child("Menu").child(itemNameFromIntent);


        //Retrieve item details from Firebase
        itemReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get item details from database
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String price = dataSnapshot.child("price").getValue(String.class);
                    String image = dataSnapshot.child("image").getValue(String.class);

                    //Set retrieved details to respective view
                    itemName.setText(name);
                    itemPrice.setText(price);
                    Glide.with(getApplicationContext()).load(image).into(itemImage);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ItemDetailsActivity.this,"Failed to load items", Toast.LENGTH_SHORT).show();
            }
        });



        //Set click listener for buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //increment quantity when Add button is pressed
                quantity++;
                updateQuantity();
            }
        });


        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //decrement quantity when Add button is pressed
                if (quantity > 0) {
                    quantity--;
                    updateQuantity();
                }
            }
        });


        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if quantity is greater than 0
                if (quantity > 0) {
                    addingToCartList();
                }
                else {
                    Toast.makeText(ItemDetailsActivity.this, "Please Select Quantity More Than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getProductDetails(String productName)
    {
        DatabaseReference menuRef = FirebaseDatabase.getInstance().getReference().child("Menu");

        menuRef.child(productName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String price = dataSnapshot.child("price").getValue(String.class);
                    String image = dataSnapshot.child("image").getValue(String.class);

                    //Set retrieved details to respective view
                    itemName.setText(name);
                    itemPrice.setText(price);
                    Glide.with(getApplicationContext()).load(image).into(itemImage);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void addingToCartList() {


        String saveCurrentDate, saveCurrentTime;


        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());


        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("CartList");

        //Getting phone number
        String userPhone = phone.getText().toString();

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("name", itemName.getText().toString());
        cartMap.put("price", itemPrice.getText().toString());
        cartMap.put("quantity", itemQuantity.getText());
        //Dk want to add below or not
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);



        /*
        cartListRef.child("UserView").child(Prevalent.currentOnlineUser.getPhone())
                .child("Products").child(itemName.getText().toString())
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            cartListRef.child("CashierView").child(Prevalent.currentOnlineUser.getPhone())
                                    .child("Products").child(itemName.getText().toString())
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ItemDetailsActivity.this, "Added to Cart List...", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(ItemDetailsActivity.this, MenuCategoryActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }


                    }
                });

         */


        cartListRef.child("UserView").child(userPhone).child("Products").child(itemName.getText().toString())
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            cartListRef.child("CashierView").child(userPhone).child("Products").child(itemName.getText().toString())
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ItemDetailsActivity.this, "Added to Cart List...", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(ItemDetailsActivity.this, MenuCategoryActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }


                    }
                });

    }


    private void updateQuantity() {
        itemQuantity.setText(String.valueOf(quantity));


    }
}