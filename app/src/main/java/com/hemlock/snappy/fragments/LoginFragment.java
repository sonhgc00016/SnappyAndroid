package com.hemlock.snappy.fragments;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hemlock.snappy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

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
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bundle = getFacebookData(object);
                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(bundle);

                        //remove all in back stack before redirect to Home Fragment
                        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
                        if (fragment != null)
                            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

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
                Log.w(getClass().getSimpleName(), "Cancel");
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
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));
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
    public void onClick(View view) {

    }
}
