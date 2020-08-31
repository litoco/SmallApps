package com.assl.androidassignment.repository.internet;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PublicAPI {

    @GET("users")
    Call<APIData> getAllData();
}
