package com.assl.androidassignment.repository.internet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APIData {

    @SerializedName("data")
    @Expose
    private ArrayList<UserDetails> userDetailsList;

    public ArrayList<UserDetails> getUserDetailsList() {
        return userDetailsList;
    }

    public void setUserDetailsList(ArrayList<UserDetails> userDetailsList) {
        this.userDetailsList = userDetailsList;
    }

    @Override
    public String toString() {
        return "APIData{" +
                "userDetailsList=" + userDetailsList +
                '}';
    }
}
