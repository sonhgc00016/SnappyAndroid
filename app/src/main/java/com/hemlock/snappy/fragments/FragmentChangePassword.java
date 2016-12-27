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
import com.hemlock.snappy.json.JSON_CommonResult;
import com.hemlock.snappy.network.ApiClient;
import com.hemlock.snappy.network.ApiInterface;
import com.hemlock.snappy.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lookonlyatme on 12/19/16.
 */

public class FragmentChangePassword extends BaseFragment implements View.OnClickListener, MainActivity.OnBackPressedListener {
    private EditText txtPassword, txtNewPassword, txtRenewPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_password, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setOnBackPressedListener(this);

        txtPassword = (EditText) v.findViewById(R.id.txt_password);
        txtPassword.setSelectAllOnFocus(true);
        txtNewPassword = (EditText) v.findViewById(R.id.txt_new_password);
        txtNewPassword.setSelectAllOnFocus(true);
        txtRenewPassword = (EditText) v.findViewById(R.id.txt_renew_password);
        txtRenewPassword.setSelectAllOnFocus(true);

        Button btnChangePassword = (Button) v.findViewById(R.id.btn_change_password);
        btnChangePassword.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_change_password:
                changePassword();
                break;
            default:
                break;
        }
    }

    private void changePassword() {
        Utils.closeSoftKeyboard(getActivity());
        String password = txtPassword.getText().toString();
        String newPassword = txtNewPassword.getText().toString();
        String renewPassword = txtRenewPassword.getText().toString();
        String accessToken = Utils.getLocalAccessToken(getActivity());

        if (validate(password, newPassword, renewPassword)) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<JSON_CommonResult> call = apiService.changePassword(accessToken, password, newPassword);
            startLoading();
            call.enqueue(new Callback<JSON_CommonResult>() {
                @Override
                public void onResponse(Call<JSON_CommonResult> call, Response<JSON_CommonResult> response) {
                    stopLoading();
                    if (response.body() != null)
                        if (response.body().getSuccess()) {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            removeAllInBackStack();
                            replaceFragment(new HomeFragment(), false);
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Log.w(getClass().getSimpleName(), "Message: " + response.body().getMessage());
                        }
                }

                @Override
                public void onFailure(Call<JSON_CommonResult> call, Throwable t) {
                    stopLoading();
                    Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                    Log.w(getClass().getSimpleName(), t.toString());
                }
            });
        }
    }

    private boolean validate(String password, String newPassword, String renewPassword) {
        if (Utils.isEmpty(password)) {
            Toast.makeText(getActivity(), getString(R.string.change_password_warning1), Toast.LENGTH_LONG).show();
            return false;
        } else if (Utils.isEmpty(newPassword)) {
            Toast.makeText(getActivity(), R.string.change_password_warning2, Toast.LENGTH_LONG).show();
            return false;
        } else if (!newPassword.equals(renewPassword)) {
            Toast.makeText(getActivity(), R.string.change_password_warning3, Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    @Override
    public void doBack() {
        popBackStack();
    }
}
