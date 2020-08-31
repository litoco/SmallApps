package com.assl.androidassignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.assl.androidassignment.repository.internet.UserDetails;
import com.assl.androidassignment.repository.Repository;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    private LiveData<ArrayList<UserDetails>> userDetailsList;
    private Repository repository = new Repository();


    public LiveData<ArrayList<UserDetails>> getRecyclerViewData() {
        if(userDetailsList==null){
            userDetailsList = repository.getDataFromInternet();
        }
        return userDetailsList;
    }
}
