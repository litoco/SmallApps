package com.assl.androidassignment.repository;

import androidx.lifecycle.LiveData;

import com.assl.androidassignment.repository.internet.PublicAPI;
import com.assl.androidassignment.repository.internet.UserDetails;
import com.assl.androidassignment.viewmodel.UserDetailListLiveData;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public LiveData<ArrayList<UserDetails>> getDataFromInternet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PublicAPI publicAPI = retrofit.create(PublicAPI.class);
        return new UserDetailListLiveData(publicAPI);
    }
}
