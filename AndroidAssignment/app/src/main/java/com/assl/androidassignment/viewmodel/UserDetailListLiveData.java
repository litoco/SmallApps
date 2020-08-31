package com.assl.androidassignment.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.assl.androidassignment.repository.internet.APIData;
import com.assl.androidassignment.repository.internet.PublicAPI;
import com.assl.androidassignment.repository.internet.UserDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailListLiveData extends LiveData<ArrayList<UserDetails>> {

    private PublicAPI publicAPI;

    public UserDetailListLiveData(PublicAPI publicAPI) {
        this.publicAPI = publicAPI;
    }

    @Override
    protected void onActive() {
        super.onActive();
        Call<APIData> apiData = publicAPI.getAllData();
        apiData.enqueue(new Callback<APIData>() {
            @Override
            public void onResponse(Call<APIData> call, Response<APIData> response) {
                Log.e("Response", response.body().toString());
                setValue(response.body().getUserDetailsList());
            }

            @Override
            public void onFailure(Call<APIData> call, Throwable t) {
                Log.e("Response", "Failed: "+t.toString());
                setValue(null);
            }
        });
    }
}
