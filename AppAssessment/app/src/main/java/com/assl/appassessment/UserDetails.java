package com.assl.appassessment;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDetails {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="email")
    private String email;


    @ColumnInfo(name="name")
    private String name;


    @ColumnInfo(name="address")
    private String address;


    @ColumnInfo(name="phoneNumber")
    private String phoneNumber;


    @ColumnInfo(name="password")
    private String password;


    @ColumnInfo(name="location")
    private String location;

    public UserDetails(){
        this.clearAllFields();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void clearAllFields(){
        name=address=email=phoneNumber=password=location="";
    }
}
