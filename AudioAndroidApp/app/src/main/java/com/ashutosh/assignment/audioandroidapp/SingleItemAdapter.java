package com.ashutosh.assignment.audioandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SingleItemAdapter extends RecyclerView.Adapter<SingleItemAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SingleItem> singleItemsList;

    public SingleItemAdapter(Context context, ArrayList<SingleItem> singleItemsList) {
        this.context = context;
        this.singleItemsList = singleItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(singleItemsList.get(position).getImageResourceId()).placeholder(R.drawable.ic_audio).into(holder.audioPreview);
        holder.positionIndicator.setText(""+(position+1));
    }

    @Override
    public int getItemCount() {
        return singleItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView audioPreview;
        private TextView positionIndicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            audioPreview = itemView.findViewById(R.id.audio_preview);
            positionIndicator = itemView.findViewById(R.id.position_indicator);
            audioPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((BaseActivity)context).getPagerAdapter().setCount(2);
                    ((BaseActivity)context).getBaseViewModel().setCount(2);
                    ((BaseActivity)context).getPagerAdapter().notifyDataSetChanged();
                    ((BaseActivity)context).getViewPager().setCurrentItem(1);
                }
            });
        }
    }
}
