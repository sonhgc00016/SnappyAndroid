package com.hemlock.snappy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hemlock.snappy.R;
import com.hemlock.snappy.activities.MainActivity;
import com.hemlock.snappy.adapters.TrackingListAdapter;
import com.hemlock.snappy.json.JSON_ListTrackingResult;
import com.hemlock.snappy.model.tracking.Tracking;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;
import com.hemlock.snappy.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by lookonlyatme on 12/15/16.
 */

public class TrackingListFragment extends BaseFragment implements TrackingListAdapter.IMyViewHolderClicks, MainActivity.OnBackPressedListener {
    private RecyclerView rcvTrackings;
    private ArrayList<Tracking> trackings;
    private TextView tvNoResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tracking_list, container, false);

        rcvTrackings = (RecyclerView) v.findViewById(R.id.rcv_trackings);
        tvNoResult = (TextView) v.findViewById(R.id.tv_no_result);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnBackPressedListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvTrackings.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rcvTrackings.setLayoutManager(layoutManager);

        String accessToken = Utils.getLocalAccessToken(getActivity());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JSON_ListTrackingResult> call = apiService.getCurrentPickedTracking(accessToken);
        startLoading();
        call.enqueue(new Callback<JSON_ListTrackingResult>() {
            @Override
            public void onResponse(Call<JSON_ListTrackingResult> call, Response<JSON_ListTrackingResult> response) {
                stopLoading();
                trackings = response.body().getTrackings();
                if (trackings.size() > 0) {
                    TrackingListAdapter adapter = new TrackingListAdapter(trackings, TrackingListFragment.this);
                    rcvTrackings.setAdapter(adapter);
                } else {
                    tvNoResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<JSON_ListTrackingResult> call, Throwable t) {
                stopLoading();
                popBackStack();
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                Log.w(getClass().getSimpleName(), t.toString());
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Tracking tracking = trackings.get(position);

        TrackingDetailsFragment trackingDetailsFragment = new TrackingDetailsFragment();

        Bundle bundle = new Bundle();
        int totalCod = Utils.calTotalCod(tracking.getServices().getIsReceiverPay(), tracking.getServices().getShippingCost(),
                tracking.getServices().getCodService().getAmount(), tracking.getServices().getCodService().getCost());
        bundle.putString(getString(R.string.tracking_id), tracking.getId());
        bundle.putString(getString(R.string.receiver_name), tracking.getTo().getName());
        bundle.putString(getString(R.string.receiver_address), tracking.getTo().getAddress() + " " + tracking.getTo().getFullAddress());
        bundle.putString(getString(R.string.receiver_phone_number), tracking.getTo().getPhoneNumber());
        bundle.putString(getString(R.string.package_desc), tracking.getServices().getPackage().getSnippet());
        bundle.putInt(getString(R.string.total_cod), totalCod);
        bundle.putString(getString(R.string.current_status), tracking.getCurrentStatus());
        bundle.putString(getString(R.string.last_note), tracking.getLastUpdate().getNote());
        trackingDetailsFragment.setArguments(bundle);

        replaceFragment(trackingDetailsFragment, true);
    }

    @Override
    public void doBack() {
        popBackStack();
    }
}
