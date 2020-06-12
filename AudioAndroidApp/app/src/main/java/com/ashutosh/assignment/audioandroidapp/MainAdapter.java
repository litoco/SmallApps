package com.ashutosh.assignment.audioandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CarouselItem> carouselItemsList;

    public MainAdapter(Context context, ArrayList<CarouselItem> carouselItemsList) {
        this.context = context;
        this.carouselItemsList = carouselItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleText.setText(carouselItemsList.get(position).getTitle());
        SingleItemAdapter singleItemAdapter = new SingleItemAdapter(context, carouselItemsList.get(position).getSingleItemArrayList());
        holder.itemHolder.setAdapter(singleItemAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.itemHolder.setLayoutManager(layoutManager);
        layoutManager.scrollToPosition(carouselItemsList.get(position).getCurrentHorizontalScroll());

        ((BaseActivity)context).getBaseViewModel().setVerticalScroll(position);
    }

    @Override
    public int getItemCount() {
        return carouselItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private RecyclerView itemHolder;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.single_item_title);
            itemHolder = itemView.findViewById(R.id.single_item_recycler_view);
            itemHolder.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if(newState==0){
                        ((BaseActivity)context).setHorizontalScrollFor(ViewHolder.this.getAdapterPosition(),
                                ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition());
                    }
                }
            });
        }
    }
}
