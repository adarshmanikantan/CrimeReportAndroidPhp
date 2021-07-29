package com.adarsh.crimereportandroidphp.retrofit.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit Citizen() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://campus.sicsglobal.co.in/Project/CrimeReporting/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
