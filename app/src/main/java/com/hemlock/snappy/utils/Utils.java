package com.hemlock.snappy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.facebook.login.LoginManager;
import com.hemlock.snappy.R;

import java.text.DecimalFormat;

/**
 * Created by lookonlyatme on 12/14/16.
 */

public class Utils {
    public static String getLocalAccessToken(FragmentActivity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(activity.getString(R.string.access_token), activity.getResources().getString(R.string.access_token));
    }

    public static void removeAllInSharedPref(FragmentActivity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(activity.getString(R.string.name));
        editor.remove(activity.getString(R.string.access_token));
        editor.apply();
        LoginManager.getInstance().logOut();
    }

    public static void closeSoftKeyboard(FragmentActivity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static int calTotalCod(boolean isReceiverPay, int shippingCost, int codAmount, int codCost) {
        int totalCod;
        if (isReceiverPay) {
            totalCod = shippingCost + codAmount + codCost;
        } else
            totalCod = codAmount;
        return totalCod;
    }

    public static String formatCurrency(FragmentActivity activity, int amount) {
        DecimalFormat formatter = new DecimalFormat(activity.getString(R.string.format_number));
        return String.format("%s%s", formatter.format(amount), activity.getString(R.string.vnd));
    }

    public static boolean isEmpty(String text) {
        return text.isEmpty() || text.length() == 0 || text.equals("");
    }

    public static void saveToSharedPref(Activity act, String name, String accessToken, String profilePic) {
        SharedPreferences sharedPref = act.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (!isEmpty(name))
            editor.putString(act.getString(R.string.name), name);
        if (!isEmpty(accessToken))
            editor.putString(act.getString(R.string.access_token), accessToken);
        if (!isEmpty(profilePic))
            editor.putString(act.getString(R.string.profile_pic), profilePic);
        editor.apply();
    }
}
