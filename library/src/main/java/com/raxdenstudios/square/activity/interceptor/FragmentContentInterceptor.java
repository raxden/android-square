package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by agomez on 02/06/2015.
 */
public interface FragmentContentInterceptor {

    View onCreateContentFragmentView(Bundle savedInstanceState);
    Fragment initContentFragment();
    void onInterceptorLoaded(FragmentContentInterceptorListener interceptor);

    interface FragmentContentInterceptorListener {

        void replaceFragment(Fragment fragment);
        void replaceFragment(Fragment fragment, boolean addToBackStack);
        void replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction);

        void removeFragment(Fragment fragment);

        View getFragmentView();
        Fragment getFragment();

    }
}
