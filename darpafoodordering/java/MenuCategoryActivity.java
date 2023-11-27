package com.example.darpafoodordering;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import com.example.darpafoodordering.MenuCategoryActivity;
import com.example.darpafoodordering.R;


public class MenuCategoryActivity extends AppCompatActivity {


    private Button foodButton;
    private Button drinksButton;
    private Button cartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category);


        foodButton = findViewById(R.id.foodCategoryButton);
        drinksButton = findViewById(R.id.drinkCategoryButton);
        cartButton = findViewById(R.id.cartButton);


        //sets the page header name during on create
        TextView pageTitle = (TextView) findViewById(R.id.page_title);
        pageTitle.setText("Choose a Category");




        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuCategoryActivity.this, FoodCategoryActivity.class);
                startActivity(intent);
            }
        });


        drinksButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuCategoryActivity.this, DrinkCategoryActivity.class);
                startActivity(intent);
            }
        });


        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCategoryActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


    }
}
