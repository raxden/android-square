package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.ButterKnifeInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.ButterKnifeInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

import butterknife.ButterKnife;

/**
 * Created by Raxden on 23/07/2016.
 */
public class ButterKnifeInterceptorImpl extends InterceptorActivityImpl
        implements ButterKnifeInterceptorDelegate {

    private ButterKnifeInterceptor mCallbacks;

    public ButterKnifeInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (ButterKnifeInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

}
