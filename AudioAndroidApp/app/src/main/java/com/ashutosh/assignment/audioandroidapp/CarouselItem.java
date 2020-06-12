package com.ashutosh.assignment.audioandroidapp;

import java.util.ArrayList;

public class CarouselItem {

    private String title;
    private ArrayList<SingleItem> singleItemArrayList;
    private int currentHorizontalScroll=0;

    public CarouselItem(String title, ArrayList<SingleItem> singleItemArrayList) {
        this.title = title;
        this.singleItemArrayList = singleItemArrayList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<SingleItem> getSingleItemArrayList() {
        return singleItemArrayList;
    }

    public void setSingleItemArrayList(ArrayList<SingleItem> singleItemArrayList) {
        this.singleItemArrayList = singleItemArrayList;
    }

    public int getCurrentHorizontalScroll() {
        return currentHorizontalScroll;
    }

    public void setCurrentHorizontalScroll(int currentHorizontalScroll) {
        this.currentHorizontalScroll = currentHorizontalScroll;
    }
}
