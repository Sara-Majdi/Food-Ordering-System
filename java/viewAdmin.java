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

public class viewAdmin extends AppCompatActivity {

    RecyclerView recyclerView;
    ViewAdminAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_admin_recycler_view);

        TextView pageTitle = (TextView) findViewById(R.id.page_title);

        pageTitle.setText("Registered Users");

        ImageView backBtn = (ImageView) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewAdmin.this, adminLock.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.view_users_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewAdmin.this));

        FirebaseRecyclerOptions<ViewAdminsHelperClass> options =
                new FirebaseRecyclerOptions.Builder<ViewAdminsHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("admin"), ViewAdminsHelperClass.class)
                        .build();

        mainAdapter = new ViewAdminAdapter(options);
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
