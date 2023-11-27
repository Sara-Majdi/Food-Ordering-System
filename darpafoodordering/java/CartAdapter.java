package com.example.darpafoodordering;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartAdapter extends FirebaseRecyclerAdapter<CartHelperClass,CartAdapter.viewHolder>{

    private Button ConfirmBtn;
    private TextView totalTextPrice;
    private double totalPrice = 0;


    public CartAdapter(@NonNull FirebaseRecyclerOptions<CartHelperClass> options) {
        super(options);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (int i = 0; i < getItemCount(); i++) {
            CartHelperClass model = getItem(i);
            if (model != null){
                double totalPriceProduct = Double.parseDouble(model.getPrice()) * Double.parseDouble(model.getQuantity());
                total += totalPriceProduct;
            }
        }
        return total;
    }


    @Override
    protected void onBindViewHolder(@NonNull CartAdapter.viewHolder holder, int position, @NonNull CartHelperClass model) {
        holder.name.setText(model.getName());
        holder.price.setText("Price : " + model.getPrice());
        holder.quantity.setText("Quantity : " + model.getQuantity());
/*
        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .error(R.drawable.foodhome)
                .into(holder.image);

 */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence options[] = new CharSequence[]
                        {
                                "Edit",
                                "Delete"
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Cart Options");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("CartList");
                        String currentUserPhone = Prevalent.currentOnlineUser.getPhone();

                        if (i == 0)
                        {
                            //Edit fuction
                            Intent intent = new Intent(view.getContext(),ItemDetailsActivity.class);
                            intent.putExtra("name", model.getName());
                            view.getContext().startActivity(intent);
                        }
                        else if (i == 1)
                        {
                            //Delete function
                            cartListRef.child("UserView")
                                //.child(currentUserPhone)
                                    .child("Products")
                                    .child(model.getName())
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>(){
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(view.getContext(), "Item has been removed", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(view.getContext(),MenuCategoryActivity.class);
                                                view.getContext().startActivity(intent);
                                            } else {
                                                // Handle error here
                                                Toast.makeText(view.getContext(), "Failed to delete item", Toast.LENGTH_SHORT).show();
                                                // Log the error message
                                                Log.e("DeleteItem", "Error: " + task.getException());
                                            }
                                        }
                                    });
                        }

                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public CartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
        return new CartAdapter.viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{

       // ImageView image;
        TextView name, price, quantity;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            //image = (ImageView) itemView.findViewById(R.id.itemImage);

            name = (TextView) itemView.findViewById(R.id.cart_ProductName);
            price = (TextView) itemView.findViewById(R.id.cart_ProductPrice);
            quantity = (TextView) itemView.findViewById(R.id.cart_ProductQuantity);

        }
    }
}
