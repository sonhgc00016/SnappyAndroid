package com.hemlock.snappy.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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
import com.hemlock.snappy.json.JSON_CommonResult;
import com.hemlock.snappy.location.GPSTracker;
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
    private TextView tvName, tvMailmanBalance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        name = sharedPref.getString(getString(R.string.name), getResources().getString(R.string.name));
        accessToken = sharedPref.getString(getString(R.string.access_token), getResources().getString(R.string.access_token));

        tvName = (TextView) v.findViewById(R.id.tv_name);
        tvName.setOnClickListener(this);
        tvMailmanBalance = (TextView) v.findViewById(R.id.tv_mailman_balance);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnBackPressedListener(this);

        Button btnLogout = (Button) v.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);

        Button btnScanBarcode = (Button) v.findViewById(R.id.btn_scan_barcode);
        btnScanBarcode.setOnClickListener(this);

        Button btnTransferAll = (Button) v.findViewById(R.id.btn_transfer_all);
        btnTransferAll.setOnClickListener(this);

        Button btnPickedUpTrackings = (Button) v.findViewById(R.id.btn_picked_up_trackings);
        btnPickedUpTrackings.setOnClickListener(this);

        Button btnShippingTrackings = (Button) v.findViewById(R.id.btn_shipping_trackings);
        btnShippingTrackings.setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName.setText(name);
        tvName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        auth();
    }

    private void auth() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

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
                        Utils.saveToSharedPref(getActivity(), response.body().getUser().getName(), "", "");
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
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
    public void onResume() {
        super.onResume();
        auth();
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
            case R.id.btn_transfer_all:
                transferAll();
                break;
            case R.id.btn_picked_up_trackings:
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.picked_up_trackings), getString(R.string.picked_up_trackings));
                TrackingListFragment trackingListFragment = new TrackingListFragment();
                trackingListFragment.setArguments(bundle);
                replaceFragment(trackingListFragment, true);
                break;
            case R.id.tv_name:
                replaceFragment(new FragmentChangePassword(), true);
                break;
            case R.id.btn_shipping_trackings:
                replaceFragment(new TrackingListFragment(), true);
                break;
            default:
                break;
        }
    }

    private void scanBarcode() {
        IntentIntegrator.forSupportFragment(this)
                .setCaptureActivity(CaptureActivityPortrait.class)
                .setOrientationLocked(true)
                .initiateScan();
    }

    private void logout() {
        Utils.removeAllInSharedPref(getActivity());
        removeAllInBackStack();
        replaceFragment(new LoginFragment(), true);
    }

    private void transferAll() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(R.string.warning);
        alertDialog.setMessage(R.string.transfer_all_warning_message);
        alertDialog.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                GPSTracker gps = new GPSTracker(getActivity());
                // check if GPS enabled
                if (gps.canGetLocation()) {
                    double lat = gps.getLatitude();
                    double lon = gps.getLongitude();
                    if (lat != 0.0 && lon != 0.0) {
                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Call<JSON_CommonResult> call = apiService.transferAll(accessToken, lat, lon);

                        startLoading();
                        call.enqueue(new Callback<JSON_CommonResult>() {
                            @Override
                            public void onResponse(Call<JSON_CommonResult> call, Response<JSON_CommonResult> response) {
                                stopLoading();
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<JSON_CommonResult> call, Throwable t) {
                                stopLoading();
                                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                                Log.w(getClass().getSimpleName(), t.toString());
                            }
                        });
                    } else
                        Toast.makeText(getActivity(), R.string.get_location_fail, Toast.LENGTH_LONG).show();
                } else
                    gps.showSettingsAlert();
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            if (scanResult.getContents() == null) {
                Toast.makeText(getActivity(), R.string.cancel, Toast.LENGTH_LONG).show();
            } else {
                Pattern p = Pattern.compile("(S|E)[0-9]{8,10}");
                Matcher m = p.matcher(scanResult.getContents());
                if (m.find()) {
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
