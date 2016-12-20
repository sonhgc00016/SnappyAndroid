package com.hemlock.snappy.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hemlock.snappy.R;
import com.hemlock.snappy.activities.MainActivity;
import com.hemlock.snappy.json.JSON_CommonResult;
import com.hemlock.snappy.json.JSON_TrackingResult;
import com.hemlock.snappy.location.GPSTracker;
import com.hemlock.snappy.model.tracking.Tracking;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;
import com.hemlock.snappy.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lookonlyatme on 12/14/16.
 */

public class TrackingDetailsFragment extends BaseFragment implements View.OnClickListener, MainActivity.OnBackPressedListener {
    private TextView tvTrackingId, tvReceiverName, tvReceiverAddress, tvReceiverPhoneNumber, tvPackageDesc, tvTotalCod, tvCurrentStatus, tvLastNote;
    private String trackingId, accessToken;
    private ApiInterface apiService;
    private Button btnPickedUp, btnOnTheWay, btnOutOfDelivery, btnDelivered, btnUndeliverable, btnReturning, btnReturned;
    private EditText txtNewNote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tracking_details, container, false);

        tvTrackingId = (TextView) v.findViewById(R.id.tv_tracking_id);
        tvReceiverName = (TextView) v.findViewById(R.id.tv_receiver_name);
        tvReceiverAddress = (TextView) v.findViewById(R.id.tv_receiver_address);
        tvReceiverPhoneNumber = (TextView) v.findViewById(R.id.tv_receiver_phone_number);
        tvPackageDesc = (TextView) v.findViewById(R.id.tv_package_desc);
        tvTotalCod = (TextView) v.findViewById(R.id.tv_total_cod);
        tvCurrentStatus = (TextView) v.findViewById(R.id.tv_current_status);
        tvLastNote = (TextView) v.findViewById(R.id.tv_last_note);

        txtNewNote = (EditText) v.findViewById(R.id.tv_new_note);

        btnPickedUp = (Button) v.findViewById(R.id.btn_picked_up);
        btnPickedUp.setOnClickListener(this);
        btnOnTheWay = (Button) v.findViewById(R.id.btn_on_the_way);
        btnOnTheWay.setOnClickListener(this);
        btnOutOfDelivery = (Button) v.findViewById(R.id.btn_out_for_delivery);
        btnOutOfDelivery.setOnClickListener(this);
        btnDelivered = (Button) v.findViewById(R.id.btn_delivered);
        btnDelivered.setOnClickListener(this);
        btnUndeliverable = (Button) v.findViewById(R.id.btn_undeliverable);
        btnUndeliverable.setOnClickListener(this);
        btnReturning = (Button) v.findViewById(R.id.btn_returning);
        btnReturning.setOnClickListener(this);
        btnReturned = (Button) v.findViewById(R.id.btn_returned);
        btnReturned.setOnClickListener(this);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnBackPressedListener(this);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        trackingId = bundle.getString(getString(R.string.tracking_id));
        accessToken = Utils.getLocalAccessToken(getActivity());

        if (bundle.getString(getString(R.string.receiver_name)) != null && bundle.getString(getString(R.string.receiver_phone_number)) != null) {
            String currentStatus = bundle.getString(getString(R.string.current_status));
            checkCurrentStatus(currentStatus);
            tvTrackingId.setText(trackingId);
            tvReceiverName.setText(bundle.getString(getString(R.string.receiver_name)));
            tvReceiverAddress.setClickable(true);
            tvReceiverAddress.setMovementMethod(LinkMovementMethod.getInstance());
            tvReceiverAddress.setText(Utils.fromHtml(bundle.getString(getString(R.string.receiver_address))));
            tvReceiverPhoneNumber.setText(bundle.getString(getString(R.string.receiver_phone_number)));
            tvPackageDesc.setText(bundle.getString(getString(R.string.package_desc)));
            int totalCod = bundle.getInt(getString(R.string.total_cod));
            tvTotalCod.setText(Utils.formatCurrency(getActivity(), totalCod));
            tvCurrentStatus.setText(currentStatus);
            tvCurrentStatus.setTextColor(Color.parseColor(bundle.getString(getString(R.string.status_color))));
            tvLastNote.setText(bundle.getString(getString(R.string.last_note)));
        } else {
            Call<JSON_TrackingResult> call = apiService.showTracking(trackingId, accessToken);
            startLoading();
            call.enqueue(new Callback<JSON_TrackingResult>() {
                @Override
                public void onResponse(Call<JSON_TrackingResult> call, Response<JSON_TrackingResult> response) {
                    stopLoading();
                    if (response.body() != null)
                        if (response.body().getSuccess()) {
                            Tracking tracking = response.body().getTracking();
                            checkCurrentStatus(tracking.getCurrentStatus());
                            tvTrackingId.setText(tracking.getId());
                            tvReceiverName.setText(tracking.getTo().getName());
                            tvReceiverAddress.setText(tracking.getTo().getAddress() + " " + tracking.getTo().getFullAddress());
                            tvReceiverPhoneNumber.setText(tracking.getTo().getPhoneNumber());
                            tvPackageDesc.setText(tracking.getServices().getPackage().getSnippet());
                            int totalCod = Utils.calTotalCod(tracking.getServices().getIsReceiverPay(), tracking.getServices().getShippingCost(),
                                    tracking.getServices().getCodService().getAmount(), tracking.getServices().getCodService().getCost());
                            tvTotalCod.setText(Utils.formatCurrency(getActivity(), totalCod));
                            tvCurrentStatus.setText(tracking.getCurrentStatus());
                            tvCurrentStatus.setTextColor(Color.parseColor(tracking.getStatusColor()));
                            tvLastNote.setText(tracking.getLastUpdate().getNote());
                        } else {
                            Log.w(getClass().getSimpleName(), "Message: " + response.body().getMessage());
                        }
                }

                @Override
                public void onFailure(Call<JSON_TrackingResult> call, Throwable t) {
                    stopLoading();
                    Log.w(getClass().getSimpleName(), t.toString());
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_picked_up:
                updateTrackingStatus(getString(R.string.picked_up));
                break;
            case R.id.btn_on_the_way:
                updateTrackingStatus(getString(R.string.on_the_way));
                break;
            case R.id.btn_out_for_delivery:
                updateTrackingStatus(getString(R.string.out_for_delivery));
                break;
            case R.id.btn_delivered:
                updateTrackingStatus(getString(R.string.delivered));
                break;
            case R.id.btn_undeliverable:
                updateTrackingStatus(getString(R.string.undeliverable));
                break;
            case R.id.btn_returning:
                updateTrackingStatus(getString(R.string.returning));
                break;
            case R.id.btn_returned:
                updateTrackingStatus(getString(R.string.returned));
                break;
            default:
                break;
        }
    }

    private void updateTrackingStatus(String status) {
        GPSTracker gps = new GPSTracker(getActivity());
        // check if GPS enabled
        if (gps.canGetLocation()) {
            double lat = gps.getLatitude();
            double lon = gps.getLongitude();

            if (lat != 0.0 && lon != 0.0) {
                Utils.closeSoftKeyboard(getActivity());
                String note = txtNewNote.getText().toString();
                Call<JSON_CommonResult> call = apiService.updateStatus(accessToken, trackingId, status, note, lat, lon);
                startLoading();
                call.enqueue(new Callback<JSON_CommonResult>() {
                    @Override
                    public void onResponse(Call<JSON_CommonResult> call, Response<JSON_CommonResult> response) {
                        stopLoading();
                        if (response.body() != null) {
                            if (response.body().getMessage() != null)
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            if (response.body().getSuccess()) {
                                removeAllInBackStack();
                                replaceFragment(new HomeFragment(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JSON_CommonResult> call, Throwable t) {
                        stopLoading();
                        popBackStack();
                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                        Log.w(getClass().getSimpleName(), t.toString());
                    }
                });
            }
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    private void checkCurrentStatus(String currentStatus) {
        switch (currentStatus) {
            case "Đã khởi tạo":
                btnPickedUp.setVisibility(View.VISIBLE);
                btnOnTheWay.setVisibility(View.GONE);
                btnOutOfDelivery.setVisibility(View.GONE);
                btnDelivered.setVisibility(View.GONE);
                btnUndeliverable.setVisibility(View.GONE);
                btnReturning.setVisibility(View.GONE);
                btnReturned.setVisibility(View.GONE);
                break;
            case "Đã nhận hàng":
                btnPickedUp.setVisibility(View.GONE);
                btnOnTheWay.setVisibility(View.VISIBLE);
                btnOutOfDelivery.setVisibility(View.GONE);
                btnDelivered.setVisibility(View.GONE);
                btnUndeliverable.setVisibility(View.GONE);
                btnReturning.setVisibility(View.GONE);
                btnReturned.setVisibility(View.GONE);
                break;
            case "Đang vận chuyển":
                btnPickedUp.setVisibility(View.GONE);
                btnOnTheWay.setVisibility(View.GONE);
                btnOutOfDelivery.setVisibility(View.VISIBLE);
                btnDelivered.setVisibility(View.GONE);
                btnUndeliverable.setVisibility(View.GONE);
                btnReturning.setVisibility(View.GONE);
                btnReturned.setVisibility(View.GONE);
                break;
            case "Đang giao hàng":
                btnPickedUp.setVisibility(View.GONE);
                btnOnTheWay.setVisibility(View.GONE);
                btnOutOfDelivery.setVisibility(View.VISIBLE);
                btnDelivered.setVisibility(View.VISIBLE);
                btnUndeliverable.setVisibility(View.VISIBLE);
                btnReturning.setVisibility(View.GONE);
                btnReturned.setVisibility(View.GONE);
                break;
            case "Giao không thành công":
                btnPickedUp.setVisibility(View.GONE);
                btnOnTheWay.setVisibility(View.GONE);
                btnOutOfDelivery.setVisibility(View.GONE);
                btnDelivered.setVisibility(View.GONE);
                btnUndeliverable.setVisibility(View.GONE);
                btnReturning.setVisibility(View.VISIBLE);
                btnReturned.setVisibility(View.GONE);
                break;
            case "Đang hoàn":
                btnPickedUp.setVisibility(View.GONE);
                btnOnTheWay.setVisibility(View.GONE);
                btnOutOfDelivery.setVisibility(View.GONE);
                btnDelivered.setVisibility(View.GONE);
                btnUndeliverable.setVisibility(View.GONE);
                btnReturning.setVisibility(View.GONE);
                btnReturned.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void doBack() {
        popBackStack();
    }
}
