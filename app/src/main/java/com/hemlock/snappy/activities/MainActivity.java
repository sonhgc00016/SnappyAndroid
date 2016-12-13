package com.hemlock.snappy.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.hemlock.snappy.R;
import com.hemlock.snappy.fragments.HomeFragment;
import com.hemlock.snappy.fragments.LoginFragment;

public class MainActivity extends FragmentActivity {

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

        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;

        //add first fragment
        if (loggedIn) {
            addFragment(HomeFragment.class);
        } else {
            addFragment(LoginFragment.class);
        }
    }

    public void addFragment(Class<?> cls) {
        Fragment fm;
        try {
            fm = (Fragment) cls.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fm, cls.getName()).commit();
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
