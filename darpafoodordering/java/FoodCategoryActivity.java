package com.example.darpafoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FoodCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView Box1,Box2,Box3,Box4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);

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
        pageTitle.setText("Food");

    }
        @Override
        public void onClick (View view){
            Intent i;

            if (view.getId() == R.id.box1) {
                i = new Intent(this, BurgerMenu.class);
                startActivity(i);
            } else if (view.getId() == R.id.box2) {
                i = new Intent(this, PastaMenu.class);
                startActivity(i);
            } else if (view.getId() == R.id.box3) {
                i = new Intent(this, WesternMenu.class);
                startActivity(i);
            } else if (view.getId() == R.id.box4) {
                i = new Intent(this, VegetarianMenu.class);
                startActivity(i);
            }
        }
    }