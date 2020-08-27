package com.assl.appassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllUsersRecyclerView extends RecyclerView.Adapter<AllUsersRecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<UserDetails> allUsers;

    public AllUsersRecyclerView(Context context, ArrayList<UserDetails> allUsers) {
        this.context = context;
        this.allUsers = allUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(context).inflate(R.layout.user_details, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(allUsers.get(position).getName());
        holder.address.setText(allUsers.get(position).getAddress());
        holder.email.setText(allUsers.get(position).getEmail());
        holder.location.setText(allUsers.get(position).getLocation());
        holder.phoneNumber.setText(allUsers.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, address, email, phoneNumber, location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            email = itemView.findViewById(R.id.email);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            location = itemView.findViewById(R.id.location);
        }
    }
}
