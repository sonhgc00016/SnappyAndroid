package com.hemlock.snappy.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hemlock.snappy.R;
import com.hemlock.snappy.activities.MainActivity;
import com.hemlock.snappy.json.JSON_TrackingResult;
import com.hemlock.snappy.model.Tracking;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lookonlyatme on 12/12/16.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, MainActivity.OnBackPressedListener {
    private String name;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        name = sharedPref.getString(getString(R.string.name), getResources().getString(R.string.name));

        TextView tvName = (TextView) v.findViewById(R.id.tv_name);
        tvName.setText(getString(R.string.hello) + " " + name);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnBackPressedListener(this);
        linearLayout = (LinearLayout) mainActivity.findViewById(R.id.activity_main);

        Button btnLogout = (Button) v.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);

        Button btnScanBarcode = (Button) v.findViewById(R.id.btn_scan_barcode);
        btnScanBarcode.setOnClickListener(this);

        Button btnTransferAddress = (Button) v.findViewById(R.id.btn_transfer_address);
        btnTransferAddress.setOnClickListener(this);

        Button btnTrackingList = (Button) v.findViewById(R.id.btn_tracking_list);
        btnTrackingList.setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String accessToken = "";

        Call<JSON_TrackingResult> call = apiService.showTracking("E80000763", accessToken);
        call.enqueue(new Callback<JSON_TrackingResult>() {
            @Override
            public void onResponse(Call<JSON_TrackingResult> call, Response<JSON_TrackingResult> response) {
                if (response.body().getSuccess()) {
                    Tracking tracking = response.body().getTracking();
                } else {
                    Log.w(getClass().getSimpleName(), "Message: " + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<JSON_TrackingResult> call, Throwable t) {
                Log.w(getClass().getSimpleName(), t.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_logout:
                logout();
                break;
            case R.id.btn_scan_barcode:
                scanBarcode();
                break;
            case R.id.btn_transfer_address:
                break;
            case R.id.btn_tracking_list:
                break;
            default:
                break;
        }
    }

    private void scanBarcode() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.initiateScan();
    }

    private void logout() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(getString(R.string.name));
        editor.remove(getString(R.string.access_token));
        editor.apply();
        LoginManager.getInstance().logOut();
        removeAllInBackStack();
        replaceFragment(new LoginFragment(), true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String re = scanResult.getContents();
            Snackbar.make(linearLayout, re, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void doBack() {
        System.exit(1);
    }
}
