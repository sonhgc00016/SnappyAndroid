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

    @GET("snappy/users/me")
    Call<JSON_AuthResult> auth(@Query("access_token") String accessToken);

    @POST("users/login/password")
    Call<JSON_LoginResult> loginEmail(
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("snappy/users/login/password")
    Call<JSON_LoginResult> loginPhoneNumber(
            @Query("phone_number") String phoneNumber,
            @Query("password") String password
    );

    @POST("snappy/users/login")
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

    @GET("snappy/management/trackings/trackings_by_mailman_shipping")
    Call<JSON_ListTrackingResult> getCurrentShippingTracking(@Query("access_token") String accessToken);

    @POST("snappy/users")
    Call<JSON_LoginResult> register(
            @Query("name") String name,
            @Query("phone_number") String phoneNumber,
            @Query("email") String email,
            @Query("password") String password
    );

    @POST("snappy/users/me")
    Call<JSON_CommonResult> changePassword(
            @Query("access_token") String accessToken,
            @Query("current_password") String currentPassword,
            @Query("new_password") String newPassword
    );

    @POST("snappy/management/trackings/mailman_transfer_all")
    Call<JSON_CommonResult> transferAll(
            @Query("access_token") String accessToken,
            @Query("lat") double lat,
            @Query("lon") double lon
    );
}
