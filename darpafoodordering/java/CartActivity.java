package com.example.darpafoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PrivateKey;

public class CartActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    CartAdapter mainAdapter;

    private TextView totalTextPrice;
    private TextView textPrice;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        drawerLayout = findViewById(R.id.drawer);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));

        FirebaseRecyclerOptions<CartHelperClass> options =
                new FirebaseRecyclerOptions.Builder<CartHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CartList")
                                .child("UserView").child("Products"), CartHelperClass.class)
                        .build();

        mainAdapter = new CartAdapter(options);
        recyclerView.setAdapter(mainAdapter);
        mainAdapter.startListening();

        // page title
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Cart");

        //find the textView to display total price
        textPrice = findViewById(R.id.textPrice);

        //Initialize the "checkPrice" btn
        Button checkPriceBtn = findViewById(R.id.checkPrice);

        checkPriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotalPrice();
            }
        });
    }

    private void updateTotalPrice() {
        double totalPrice = mainAdapter.calculateTotalPrice();
        textPrice.setText(String.valueOf(totalPrice));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}

