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

public class addCredit extends AppCompatActivity {

    RecyclerView recyclerView;
    addCreditAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_credit_recycler_view);

        TextView pageTitle = (TextView) findViewById(R.id.page_title);

        pageTitle.setText("Add Credit");

        ImageView backBtn = (ImageView) findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addCredit.this, adminLock.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.add_credit_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(addCredit.this));

        FirebaseRecyclerOptions<addCreditHelperClass> options =
                new FirebaseRecyclerOptions.Builder<addCreditHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("admin"), addCreditHelperClass.class)
                        .build();

        mainAdapter = new addCreditAdapter(options);
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
