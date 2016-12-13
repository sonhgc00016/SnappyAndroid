package com.hemlock.snappy.fragments;

import android.support.v4.app.Fragment;

import com.hemlock.snappy.activities.MainActivity;

/**
 * Created by lookonlyatme on 12/13/16.
 */

public class BaseFragment extends Fragment {

    public void replaceFragment(Fragment fm, boolean addToBackStack) {
        ((MainActivity) getActivity()).replaceFragment(fm, addToBackStack);
    }
}
