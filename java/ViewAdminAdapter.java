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

public class ViewAdminAdapter extends FirebaseRecyclerAdapter<ViewAdminsHelperClass,ViewAdminAdapter.viewHolder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ViewAdminAdapter(@NonNull FirebaseRecyclerOptions<ViewAdminsHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull ViewAdminsHelperClass model) {
        holder.userFullName.setText(model.getFirst_name());
        holder.userCurrentPoints.setText(model.getPoints());

        Glide.with(holder.profilePic.getContext())
                .load(model.getUser_picture())
                .error(R.drawable.img)
                .into(holder.profilePic);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_admin_view, parent, false);
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{

        ImageView profilePic;
        TextView userFullName, userCurrentPoints;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profilePic = (ImageView) itemView.findViewById(R.id.profilePic);
            userFullName = (TextView) itemView.findViewById(R.id.userFullName);
            userCurrentPoints = (TextView) itemView.findViewById(R.id.userCurrentPoints);
        }
    }

}
