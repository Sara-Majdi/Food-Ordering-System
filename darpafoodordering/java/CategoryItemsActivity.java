package com.example.darpafoodordering;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CategoryItemsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryItemsAdapter categoryItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        recyclerView = findViewById(R.id.categoryItemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Retrieve selected category
        String selectedCategory = getIntent().getStringExtra("selectedCategory");

        // Query to fetch items
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Menu")
                .orderByChild("category")
                .equalTo(selectedCategory);

        FirebaseRecyclerOptions<CategoryItemHelperClass> options =
                new FirebaseRecyclerOptions.Builder<CategoryItemHelperClass>()
                        .setQuery(query, CategoryItemHelperClass.class)
                        .build();

        categoryItemsAdapter = new CategoryItemsAdapter(options);
        recyclerView.setAdapter(categoryItemsAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        categoryItemsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryItemsAdapter.stopListening();
    }
}
