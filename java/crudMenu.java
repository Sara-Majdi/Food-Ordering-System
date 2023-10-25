package com.example.foodorderingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class crudMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    crudMenuAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_menu_recycler_view);

        TextView pageTitle = (TextView) findViewById(R.id.page_title);

        pageTitle.setText("Menu");

        ImageView backBtn = (ImageView) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(crudMenu.this, adminLock.class);
                startActivity(intent);
            }
        });

        ImageView btnAdd = (ImageView) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (crudMenu.this, addFood.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.crud_menu_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(crudMenu.this));

        FirebaseRecyclerOptions<crudMenuHelperClass> options =
                new FirebaseRecyclerOptions.Builder<crudMenuHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("menu"), crudMenuHelperClass.class)
                        .build();

        mainAdapter = new crudMenuAdapter(options);
        recyclerView.setAdapter(mainAdapter);
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
