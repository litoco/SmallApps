package com.ashutosh.assignment.audioandroidapp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class BaseViewModel extends ViewModel {

    private ArrayList<CarouselItem> carouselItemsList;
    private int verticalScroll = 0;
    private int timeElapsed=0;
    private int count=1;

    public ArrayList<CarouselItem> getCarouselItemsList() {
        return carouselItemsList;
    }

    public void setCarouselItemsList(ArrayList<CarouselItem> carouselItemsList) {
        this.carouselItemsList = carouselItemsList;
    }

    public int getVerticalScroll() {
        return 0;
    }

    public void setVerticalScroll(int verticalScroll) {
        this.verticalScroll = verticalScroll;
    }

    public void setElapsedTime(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setCount(int i) {
        count = i;
    }

    public int getCount() {
        return count;
    }
}
