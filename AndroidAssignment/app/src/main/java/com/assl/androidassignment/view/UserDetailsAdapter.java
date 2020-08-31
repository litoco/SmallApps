package com.assl.androidassignment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assl.androidassignment.R;
import com.assl.androidassignment.repository.internet.UserDetails;

import java.util.ArrayList;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserDetails> userDetailsList;


    public UserDetailsAdapter(Context context, ArrayList<UserDetails> userDetailsList) {
        this.context = context;
        this.userDetailsList = userDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_detail, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(userDetailsList.get(position).getActivityStatus().trim().equals("Active"))
            holder.activeStatus.setImageDrawable(context.getDrawable(R.drawable.green_circle));
        else
            holder.activeStatus.setImageDrawable(context.getDrawable(R.drawable.red_circle));
        holder.name.setText(userDetailsList.get(position).getUsername());
        holder.email.setText(userDetailsList.get(position).getEmail());
        holder.gender.setText(userDetailsList.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return userDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, email, gender;
        private ImageView activeStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            gender = itemView.findViewById(R.id.gender);
            activeStatus = itemView.findViewById(R.id.active_status);
        }
    }
}
