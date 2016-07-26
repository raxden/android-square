package com.raxdenstudios.square.activity.interceptor.callback;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface NavigationDrawerInterceptorCallback extends InterceptorCallback {

    void open();

    void close();

    boolean isOpen();

    int addFragment(Fragment fragment);

    int addFragment(Fragment fragment, int[] animations);

    int addFragment(Fragment fragment, FragmentTransaction fragmentTransaction);

    int replaceFragment(Fragment fragment);

    int replaceFragment(Fragment fragment, boolean addToBackStack);

    int replaceFragment(Fragment fragment, boolean addToBackStack, int[] animations);

    int replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction);

    int removeFragment(Fragment fragment);

    int removeFragment(Fragment fragment, FragmentTransaction fragmentTransaction);

    View getFragmentView();

    Fragment getFragment();

}
