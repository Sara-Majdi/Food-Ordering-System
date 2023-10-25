package com.example.foodorderingsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class crudMenuAdapter extends FirebaseRecyclerAdapter<crudMenuHelperClass,crudMenuAdapter.viewHolder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public crudMenuAdapter(@NonNull FirebaseRecyclerOptions<crudMenuHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull crudMenuHelperClass model) {
        holder.foodName.setText(model.getFood_name());
        holder.foodDesc.setText(model.getFood_desc());

        Glide.with(holder.foodPic.getContext())
                .load(model.getFood_pic())
                .error(R.drawable.img)
                .into(holder.foodPic);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crud_menu_view, parent, false);
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{

        ImageView foodPic;
        TextView foodName, foodDesc;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            foodPic = (ImageView) itemView.findViewById(R.id.foodPic);
            foodName = (TextView) itemView.findViewById(R.id.foodName);
            foodDesc = (TextView) itemView.findViewById(R.id.foodDesc);
        }
    }

}
