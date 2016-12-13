package com.hemlock.snappy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hemlock.snappy.R;
import com.hemlock.snappy.json.JSON_Tracking;
import com.hemlock.snappy.model.Tracking;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lookonlyatme on 12/12/16.
 */

public class HomeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String accessToken = "";

        Call<JSON_Tracking> call = apiService.showTracking("E80000763", accessToken);
        call.enqueue(new Callback<JSON_Tracking>() {
            @Override
            public void onResponse(Call<JSON_Tracking> call, Response<JSON_Tracking> response) {
                if (response.body().getSuccess()) {
                    Tracking tracking = response.body().getTracking();
                } else {
                    Log.w(getClass().getSimpleName(), "Message: " + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<JSON_Tracking> call, Throwable t) {
                Log.w(getClass().getSimpleName(), t.toString());
            }
        });
    }
}
