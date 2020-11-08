package com.flyit.application.fragments.utils;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelStore;

import com.flyit.application.R;

public class FragmentUtils {
    public static void changeFragment(ViewModelStore viewModelStore, FragmentManager fragmentManager, Fragment fragment, String TAG, Bundle bundle, int container) {
        viewModelStore.clear();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        Fragment frag = fragmentManager.findFragmentByTag(TAG);
        if (frag == null) {
            frag = fragment;
        }

        if (bundle != null) {
            frag.setArguments(bundle);
        }

        ft.addToBackStack(null);
        ft.replace(container, frag);
        ft.commit();
    }
}
