package com.raxdenstudios.square.interceptor.icepick;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;

import icepick.Icepick;

/**
 * Created by Ángel Gómez on 11/02/2017.
 */

public class IcepickFragmentInterceptorImpl extends FragmentInterceptor<IcepickInterceptorCallback> implements IcepickInterceptor {

    public IcepickFragmentInterceptorImpl(@NonNull Fragment fragment) {
        super(fragment);
    }

    public IcepickFragmentInterceptorImpl(@NonNull Fragment fragment, @NonNull IcepickInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

}
