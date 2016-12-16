package com.hemlock.snappy.fragments;

import android.support.v4.app.Fragment;

import com.hemlock.snappy.R;
import com.hemlock.snappy.activities.MainActivity;

/**
 * Created by lookonlyatme on 12/13/16.
 */

public class BaseFragment extends Fragment {

    public void replaceFragment(Fragment fm, boolean addToBackStack) {
        ((MainActivity) getActivity()).replaceFragment(fm, addToBackStack);
    }

    public void removeAllInBackStack() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    public void startLoading() {
        ((MainActivity) getActivity()).startLoading();
    }

    public void stopLoading() {
        ((MainActivity) getActivity()).stopLoading();
    }

    public void popBackStack() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
