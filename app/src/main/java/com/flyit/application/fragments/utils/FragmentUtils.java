package com.flyit.application.fragments.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.flyit.application.R;

public class FragmentUtils {
    public static void changeFragment(FragmentActivity activity, FragmentManager fragmentManager, Fragment fragment, String TAG) {
        activity.getViewModelStore().clear();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        Fragment frag = fragmentManager.findFragmentByTag(TAG);
        if (frag == null) {
            frag = fragment;
        }

        ft.replace(R.id.fragment_container, frag);
        ft.commit();
    }
}
