package com.hemlock.snappy.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hemlock.snappy.R;
import com.hemlock.snappy.activities.MainActivity;
import com.hemlock.snappy.json.JSON_LoginResult;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * Created by lookonlyatme on 12/12/16.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private CallbackManager callbackManager;
    private LoginButton btnLoginFb;
    private EditText txtEmailPhoneNumber;
    private EditText txtPassword;
    private Button btnLogin;
    private LinearLayout linearLayout;
    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.hemlock.snappy",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.toString());
        }

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mainActivity = (MainActivity) getActivity();
        linearLayout = (LinearLayout) mainActivity.findViewById(R.id.activity_main);

        txtEmailPhoneNumber = (EditText) v.findViewById(R.id.txt_email_phone_number);
        txtPassword = (EditText) v.findViewById(R.id.txt_password);

        btnLogin = (Button) v.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        btnLoginFb = (LoginButton) v.findViewById(R.id.btn_login_fb);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        btnLoginFb.setReadPermissions("email, public_profile");
        // If using in a fragment
        btnLoginFb.setFragment(this);
        // Callback registration
        btnLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Get facebook data from login
                        Bundle bundle = getFacebookData(object);
                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(bundle);

                        removeAllInBackStack();

                        replaceFragment(homeFragment, true);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.w(getClass().getSimpleName(), getString(R.string.cancel));
            }

            @Override
            public void onError(FacebookException exception) {
                Log.w(getClass().getSimpleName(), exception.toString());
            }
        });
    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();
        try {
            String id = object.getString(getString(R.string.id));

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i(getString(R.string.profile_pic), profile_pic + "");
                bundle.putString(getString(R.string.profile_pic), profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString(getString(R.string.fbId), id);
            if (object.has(getString(R.string.first_name)) && object.has(getString(R.string.last_name))) {
                String name = object.getString(getString(R.string.first_name)) + " " + object.getString(getString(R.string.last_name));
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.name), name);
                editor.apply();
            }

            if (object.has(getString(R.string.email)))
                bundle.putString(getString(R.string.email), object.getString(getString(R.string.email)));
            if (object.has(getString(R.string.gender)))
                bundle.putString(getString(R.string.gender), object.getString(getString(R.string.gender)));
            if (object.has(getString(R.string.birthday)))
                bundle.putString(getString(R.string.birthday), object.getString(getString(R.string.birthday)));
            if (object.has(getString(R.string.location)))
                bundle.putString(getString(R.string.location), object.getJSONObject(getString(R.string.location)).getString(getString(R.string.name)));
            return bundle;
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing JSON");
            return bundle;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            String emailPhoneNumber = txtEmailPhoneNumber.getText().toString();
            String password = txtPassword.getText().toString();

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<JSON_LoginResult> call;

            if (Patterns.EMAIL_ADDRESS.matcher(emailPhoneNumber).matches()) {
                call = apiService.loginEmail(emailPhoneNumber, password);
            } else {
                call = apiService.loginPhoneNumber(emailPhoneNumber, password);
            }

            call.enqueue(new Callback<JSON_LoginResult>() {
                @Override
                public void onResponse(Call<JSON_LoginResult> call, Response<JSON_LoginResult> response) {
                    if (response.body().getSuccess()) {

                        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.name), response.body().getName());
                        editor.putString(getString(R.string.access_token), response.body().getAccessToken());
                        editor.apply();

                        HomeFragment homeFragment = new HomeFragment();

                        removeAllInBackStack();

                        replaceFragment(homeFragment, true);
                    } else {
                        Snackbar.make(linearLayout, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<JSON_LoginResult> call, Throwable t) {
                    Log.w(getClass().getSimpleName(), t.toString());
                }
            });
        }
    }
}
