package com.hemlock.snappy.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lookonlyatme on 12/9/16.
 */

public class ApiClient {
    public static final String BASE_URL = "http://192.168.1.190:4000/api/v1/snappy/management/";
//    public static final String BASE_URL = "https://pages.fm/api/v1/snappy";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
