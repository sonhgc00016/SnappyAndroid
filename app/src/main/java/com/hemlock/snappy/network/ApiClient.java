package com.hemlock.snappy.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lookonlyatme on 12/9/16.
 */

public class ApiClient {
//    base link api dev, remember change facebook_app_id in string.xml, too
//    private static final String BASE_URL = "http://192.168.1.225:4000/api/v1/";local
//    base link api prod, remember change facebook_app_id in string.xml, too
    private static final String BASE_URL = "https://pages.fm/api/v1/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }
}
