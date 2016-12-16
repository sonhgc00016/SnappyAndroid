package com.hemlock.snappy.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.hemlock.snappy.R;
import com.hemlock.snappy.fragments.HomeFragment;
import com.hemlock.snappy.fragments.LoginFragment;
import com.hemlock.snappy.json.JSON_AuthResult;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;
import com.hemlock.snappy.utils.Utils;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {
    private RelativeLayout rlLoading;
    private AVLoadingIndicatorView mLoadingView;

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    protected OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initial FB SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        setContentView(R.layout.activity_main);

        String accessToken = Utils.getLocalAccessToken(this);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<JSON_AuthResult> call = apiService.auth(accessToken);
        startLoading();
        call.enqueue(new Callback<JSON_AuthResult>() {
            @Override
            public void onResponse(Call<JSON_AuthResult> call, Response<JSON_AuthResult> response) {
                stopLoading();
                if (response.body() != null)
                    if (response.body().getSuccess()) {
                        addFragment(HomeFragment.class);
                    } else {
                        Utils.removeAllInSharedPref(MainActivity.this);
                        addFragment(LoginFragment.class);
                        Log.w(getClass().getSimpleName(), "Message: " + response.body().getMessage());
                    }
            }

            @Override
            public void onFailure(Call<JSON_AuthResult> call, Throwable t) {
                stopLoading();
                Utils.removeAllInSharedPref(MainActivity.this);
                addFragment(LoginFragment.class);
                Log.w(getClass().getSimpleName(), t.toString());
            }
        });
    }

    public void addFragment(Class<?> cls) {
        Fragment fm;
        try {
            fm = (Fragment) cls.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fm, cls.getName())
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceFragment(Fragment fm, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
        if (addToBackStack) {
            ft.replace(R.id.container, fm, fm.getClass().getName()).addToBackStack(fm.getClass().getName())
                    .commit();
        } else {
            ft.replace(R.id.container, fm, fm.getClass().getName())
                    .commit();
        }
    }

    public void startLoading() {
        rlLoading = (RelativeLayout) findViewById(R.id.rl_loading);
        rlLoading.setVisibility(View.VISIBLE);
        mLoadingView = (AVLoadingIndicatorView) findViewById(R.id.loading);
        mLoadingView.smoothToShow();
    }

    public void stopLoading() {
        rlLoading = (RelativeLayout) findViewById(R.id.rl_loading);
        rlLoading.setVisibility(View.GONE);
        mLoadingView = (AVLoadingIndicatorView) findViewById(R.id.loading);
        mLoadingView.smoothToHide();
    }

    public interface OnBackPressedListener {
        void doBack();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof LoginFragment) {
            System.exit(1);
        } else if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        } else
            super.onBackPressed();
    }
}
