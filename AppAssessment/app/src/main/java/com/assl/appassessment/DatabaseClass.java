package com.assl.appassessment;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserDetails.class}, version = 1)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract UserDetailsDAO getUserDetailsDAO();
}
