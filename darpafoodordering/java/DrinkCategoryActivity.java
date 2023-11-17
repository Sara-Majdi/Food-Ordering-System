package com.example.darpafoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DrinkCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView Box1,Box2,Box3,Box4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        Box1 = findViewById(R.id.box1);
        Box2 = findViewById(R.id.box2);
        Box3 = findViewById(R.id.box3);
        Box4 = findViewById(R.id.box4);

        Box1.setOnClickListener(this);
        Box2.setOnClickListener(this);
        Box3.setOnClickListener(this);
        Box4.setOnClickListener(this);

        //sets the page header name during on create
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Drinks");

    }
    @Override
    public void onClick (View view){
        Intent i;

        if (view.getId() == R.id.box1) {
            i = new Intent(this, WaterMenu.class);
            startActivity(i);
        } else if (view.getId() == R.id.box2) {
            i = new Intent(this, JuicesMenu.class);
            startActivity(i);
        } else if (view.getId() == R.id.box3) {
            i = new Intent(this, SmoothiesMenu.class);
            startActivity(i);
        } else if (view.getId() == R.id.box4) {
            i = new Intent(this, SodaMenu.class);
            startActivity(i);
        }
    }
}
