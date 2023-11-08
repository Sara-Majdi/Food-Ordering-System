package com.example.darpafoodordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.darpafoodordering.MenuActivity;
import com.example.darpafoodordering.R;

public class MenuCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category);

        Button foodCategoryButton = findViewById(R.id.foodCategoryButton);
        Button drinkCategoryButton = findViewById(R.id.drinkCategoryButton);

        foodCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMenuActivity("Food");
            }
        });

        drinkCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMenuActivity("Drink");
            }
        });
    }

    private void navigateToMenuActivity(String category) {
        Intent intent = new Intent(MenuCategoryActivity.this, MenuActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}