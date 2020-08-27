package com.assl.appassessment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDetailsDAO {

    @Query("SELECT * FROM UserDetails")
    LiveData<List<UserDetails>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUserDetail(UserDetails userDetails);
}
