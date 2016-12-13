package com.hemlock.snappy.network;

import com.hemlock.snappy.json.JSON_Tracking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lookonlyatme on 12/9/16.
 */

public interface ApiInterface {
    @GET("trackings/{trackingId}")
    Call<JSON_Tracking> showTracking(@Path("trackingId") String trackingId, @Query("access_token") String accessToken);
}
