package com.hemlock.snappy.network;

import com.hemlock.snappy.json.JSON_AuthResult;
import com.hemlock.snappy.json.JSON_CommonResult;
import com.hemlock.snappy.json.JSON_ListTrackingResult;
import com.hemlock.snappy.json.JSON_LoginResult;
import com.hemlock.snappy.json.JSON_TrackingResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lookonlyatme on 12/9/16.
 */

public interface ApiInterface {
    @GET("snappy/trackings/{trackingId}")
    Call<JSON_TrackingResult> showTracking(@Path("trackingId") String trackingId, @Query("access_token") String accessToken);

    @GET("snappy/me")
    Call<JSON_AuthResult> auth(@Query("access_token") String accessToken);

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
    Call<JSON_LoginResult> loginFb(
            @Query("fbId") String fbId,
            @Query("accessToken") String accessToken
    );

    @POST("snappy/management/trackings/update_status")
    Call<JSON_CommonResult> updateStatus(
            @Query("access_token") String accessToken,
            @Query("tracking_id") String trackingId,
            @Query("new_status") String newStatus,
            @Query("note") String note,
            @Query("lat") double lat,
            @Query("lon") double lon
    );

    @GET("snappy/management/trackings/trackings_by_mailman_picked_up")
    Call<JSON_ListTrackingResult> getCurrentPickedTracking(@Query("access_token") String accessToken);

    @POST("users")
    Call<JSON_LoginResult> register(
            @Query("name") String name,
            @Query("phone_number") String phoneNumber,
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("me")
    Call<JSON_CommonResult> changePassword(
            @Query("access_token") String accessToken,
            @Query("current_password") String currentPassword,
            @Query("new_password") String newPassword
    );
}
