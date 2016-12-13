package com.hemlock.snappy.network;

import com.hemlock.snappy.json.JSON_LoginResult;
import com.hemlock.snappy.json.JSON_TrackingResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lookonlyatme on 12/9/16.
 */

public interface ApiInterface {
    @GET("snappy/management/trackings/{trackingId}")
    Call<JSON_TrackingResult> showTracking(@Path("trackingId") String trackingId, @Query("access_token") String accessToken);

//    @GET("me")
//    Call<JSON_TrackingResult> showTracking(@Query("access_token") String accessToken);

    @POST("users/login/password")
    Call<JSON_LoginResult> loginEmail(
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("users/login/password")
    Call<JSON_LoginResult> loginPhoneNumber(
            @Query("phone_number") String phoneNumber,
            @Query("password") String password
    );

    @POST("users/login")
    Call<JSON_LoginResult> loginFb(@Body String fbId, String accessToken);
}
