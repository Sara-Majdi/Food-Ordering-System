package com.example.darpafoodordering;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.bumptech.glide.Glide;

public class CategoryItemsAdapter extends FirebaseRecyclerAdapter<CategoryItemHelperClass, CategoryItemsAdapter.ViewHolder> {

    public CategoryItemsAdapter(@NonNull FirebaseRecyclerOptions<CategoryItemHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull CategoryItemHelperClass model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());

        // Load item image using Glide
        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .error(R.drawable.foodhome)
                .into(holder.image);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_layout, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);
        }
    }
}