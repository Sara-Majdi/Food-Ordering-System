package com.example.darpafoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    MenuAdapterNew mainAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drawerLayout = findViewById(R.id.drawer);

        recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MenuActivity.this));

        FirebaseRecyclerOptions<MenuHelperClass> options =
                new FirebaseRecyclerOptions.Builder<MenuHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Menu"), MenuHelperClass.class)
                        .build();

        mainAdapter = new MenuAdapterNew(options);
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

