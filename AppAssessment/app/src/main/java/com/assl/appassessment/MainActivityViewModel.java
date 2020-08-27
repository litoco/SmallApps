package com.assl.appassessment;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import org.json.JSONObject;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private UserDetails userDetails;
    private DatabaseClass dbClass;


    public UserDetails getUserDetails() {
        if(userDetails==null)
            userDetails = new UserDetails();
        return userDetails;
    }

    public void storeUserDetails(JSONObject obj, Context context) {
        if(dbClass==null)
            dbClass = Room.databaseBuilder(context,
                    DatabaseClass.class, "temp_database").allowMainThreadQueries().build();
        dbClass.getUserDetailsDAO().addUserDetail(userDetails);
    }

    public LiveData<List<UserDetails>> getAllUsers(){
        return dbClass.getUserDetailsDAO().getAllUsers();
    }
}
