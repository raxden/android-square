package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by agomez on 02/06/2015.
 */
public interface FragmentContentInterceptor extends Interceptor<FragmentContentInterceptor.FragmentContentInterceptorCallback> {

    View onCreateContentFragmentView(Bundle savedInstanceState);

    Fragment initContentFragment();

    interface FragmentContentInterceptorCallback extends InterceptorCallback {

        void replaceFragment(Fragment fragment);

        void replaceFragment(Fragment fragment, boolean addToBackStack);

        void replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction);

        void removeFragment(Fragment fragment);

        View getFragmentView();

        Fragment getFragment();

    }
}
