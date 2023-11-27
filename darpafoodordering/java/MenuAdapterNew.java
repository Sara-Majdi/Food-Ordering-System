package com.example.darpafoodordering;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuAdapterNew extends FirebaseRecyclerAdapter<MenuHelperClass,MenuAdapterNew.viewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MenuAdapterNew(@NonNull FirebaseRecyclerOptions<MenuHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MenuAdapterNew.viewHolder holder, int position, @NonNull MenuHelperClass model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());

        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .error(R.drawable.foodhome)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),ItemDetailsActivity.class);
                intent.putExtra("name", model.getName());
                view.getContext().startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public MenuAdapterNew.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_layout, parent, false);
        return new MenuAdapterNew.viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name, price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.itemImage);
            name = (TextView) itemView.findViewById(R.id.itemName);
            price = (TextView) itemView.findViewById(R.id.itemPrice);
        }
    }

}