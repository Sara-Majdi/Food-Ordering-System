package com.example.darpafoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SodaMenu extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    MenuAdapterNew mainAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soda_menu);

        drawerLayout = findViewById(R.id.drawer);
        recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(SodaMenu.this));

        FirebaseRecyclerOptions<MenuHelperClass> options =
                new FirebaseRecyclerOptions.Builder<MenuHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Menu").orderByChild("foodtype").equalTo("Soda"), MenuHelperClass.class)
                        .build();

        mainAdapter = new MenuAdapterNew(options);
        recyclerView.setAdapter(mainAdapter);

        //sets the page header name during on create
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Soda");
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
