package com.flyit.application.fragments.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelStore;

import com.flyit.application.R;

public class FragmentUtils {
    public static void changeFragment(ViewModelStore viewModelStore, FragmentManager fragmentManager, Fragment fragment, String TAG) {
        viewModelStore.clear();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        Fragment frag = fragmentManager.findFragmentByTag(TAG);
        if (frag == null) {
            frag = fragment;
        }

        ft.replace(R.id.fragment_container, frag);
        ft.commit();
    }
}
