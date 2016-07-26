package com.raxdenstudios.square.activity.interceptor.delegate;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

import com.raxdenstudios.square.InterceptorDelegate;

/**
 * Created by Raxden on 22/07/2016.
 */
public interface FragmentContentInterceptorDelegate extends InterceptorDelegate {

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
