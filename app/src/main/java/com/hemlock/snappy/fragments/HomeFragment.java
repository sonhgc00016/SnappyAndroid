package com.hemlock.snappy.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hemlock.snappy.R;
import com.hemlock.snappy.activities.MainActivity;
import com.hemlock.snappy.json.JSON_AuthResult;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;
import com.hemlock.snappy.utils.Utils;
import com.hemlock.snappy.zxing.CaptureActivityPortrait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lookonlyatme on 12/12/16.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, MainActivity.OnBackPressedListener {
    private String name, accessToken;
    private ApiInterface apiService;
    private  TextView tvName, tvMailmanBalance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        name = sharedPref.getString(getString(R.string.name), getResources().getString(R.string.name));
        accessToken = sharedPref.getString(getString(R.string.access_token), getResources().getString(R.string.access_token));

        tvName = (TextView) v.findViewById(R.id.tv_name);
        tvMailmanBalance = (TextView) v.findViewById(R.id.tv_mailman_balance);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnBackPressedListener(this);

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

        tvName.setText(getString(R.string.hello) + " " + name);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<JSON_AuthResult> call = apiService.auth(accessToken);
        startLoading();
        call.enqueue(new Callback<JSON_AuthResult>() {
            @Override
            public void onResponse(Call<JSON_AuthResult> call, Response<JSON_AuthResult> response) {
                stopLoading();
                if (response.body() != null)
                    if (response.body().getSuccess()) {
                        tvMailmanBalance.setText(getString(R.string.maiman_balance) + " "
                                + Utils.formatCurrency(getActivity(), response.body().getUser().getMailmanBalance()));
                    } else {
                        Utils.removeAllInSharedPref(getActivity());
                        removeAllInBackStack();
                        replaceFragment(new LoginFragment(), false);
                        Log.w(getClass().getSimpleName(), "Message: " + response.body().getMessage());
                    }
            }

            @Override
            public void onFailure(Call<JSON_AuthResult> call, Throwable t) {
                stopLoading();
                Utils.removeAllInSharedPref(getActivity());
                removeAllInBackStack();
                replaceFragment(new LoginFragment(), false);
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
                replaceFragment(new TrackingListFragment(), true);
                break;
            default:
                break;
        }
    }

    private void scanBarcode() {
        IntentIntegrator.forSupportFragment(this).setCaptureActivity(CaptureActivityPortrait.class).setOrientationLocked(true).initiateScan();
    }

    private void logout() {
        Utils.removeAllInSharedPref(getActivity());
        removeAllInBackStack();
        replaceFragment(new LoginFragment(), true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            if (scanResult.getContents() == null) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Pattern p = Pattern.compile("(S|E)[0-9]{8,10}");
                Matcher m = p.matcher(scanResult.getContents());
                if(m.find()) {
                    String trackingId = m.group(0);
                    Bundle bundle = new Bundle();
                    bundle.putString(getString(R.string.tracking_id), trackingId);
                    bundle.putString(getString(R.string.access_token), accessToken);

                    TrackingDetailsFragment trackingDetailsFragment = new TrackingDetailsFragment();
                    trackingDetailsFragment.setArguments(bundle);

                    replaceFragment(trackingDetailsFragment, true);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.wrong_tracking_id), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void doBack() {
        System.exit(1);
    }
}