package com.raxdenstudios.square.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Ángel Gómez on 10/06/2017.
 */

public class FragmentUtils {

    public static void loadFragment(FragmentManager fm, int containerId, Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        String tag = fragment.getClass().getSimpleName();
        transaction = transaction.replace(containerId, fragment, tag);
        transaction.commit();
    }

    public static Fragment getFragment(FragmentManager fm, int containerId) {
        Fragment fragment = null;
        if (fm != null) {
            fragment = fm.findFragmentById(containerId);
        }
        return fragment;
    }

}
