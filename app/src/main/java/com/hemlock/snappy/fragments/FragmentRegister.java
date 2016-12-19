package com.hemlock.snappy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hemlock.snappy.R;
import com.hemlock.snappy.activities.MainActivity;
import com.hemlock.snappy.json.JSON_LoginResult;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;
import com.hemlock.snappy.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lookonlyatme on 12/19/16.
 */

public class FragmentRegister extends BaseFragment implements View.OnClickListener, MainActivity.OnBackPressedListener {
    private EditText txtName, txtPhoneNumber, txtEmail, txtPassword, txtRePassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnBackPressedListener(this);

        txtName = (EditText) v.findViewById(R.id.txt_name);
        txtPhoneNumber = (EditText) v.findViewById(R.id.txt_phone_number);
        txtEmail = (EditText) v.findViewById(R.id.txt_email);
        txtPassword = (EditText) v.findViewById(R.id.txt_password);
        txtRePassword = (EditText) v.findViewById(R.id.txt_re_password);

        Button btnRegister = (Button) v.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_register:
                register();
                break;
            default:
                break;
        }
    }

    private boolean validate(String name, String phoneNumber, String password, String rePassword) {
        if (Utils.isEmpty(name) || Utils.isEmpty(phoneNumber) || Utils.isEmpty(password)) {
            stopLoading();
            Toast.makeText(getActivity(), R.string.register_warning, Toast.LENGTH_LONG).show();
            return false;
        } else if (Utils.isEmpty(rePassword)) {
            stopLoading();
            Toast.makeText(getActivity(), R.string.register_warning2, Toast.LENGTH_LONG).show();
            return false;
        } else if (!password.equals(rePassword)) {
            stopLoading();
            Toast.makeText(getActivity(), R.string.register_warning3, Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void register() {
        startLoading();
        final String name = txtName.getText().toString();
        String phoneNumber = txtPhoneNumber.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String rePassword = txtRePassword.getText().toString();

        if (validate(name, phoneNumber, password, rePassword)) {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<JSON_LoginResult> call = apiService.register(name, phoneNumber, email, password);
            startLoading();
            call.enqueue(new Callback<JSON_LoginResult>() {
                @Override
                public void onResponse(Call<JSON_LoginResult> call, Response<JSON_LoginResult> response) {
                    stopLoading();
                    if (response.body() != null)
                        if (response.body().getSuccess()) {
                            Utils.saveToSharedPref(getActivity(), name, response.body().getAccessToken(), "");
                            removeAllInBackStack();
                            Utils.closeSoftKeyboard(getActivity());
                            replaceFragment(new HomeFragment(), false);
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Log.w(getClass().getSimpleName(), "Message: " + response.body().getMessage());
                        }
                }

                @Override
                public void onFailure(Call<JSON_LoginResult> call, Throwable t) {
                    stopLoading();
                    Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                    Log.w(getClass().getSimpleName(), t.toString());
                }
            });
        }
    }

    @Override
    public void doBack() {
        popBackStack();
    }
}
