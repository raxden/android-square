package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.BundleExtrasInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.BundleExtrasInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleExtrasInterceptorImpl extends InterceptorActivityImpl
        implements BundleExtrasInterceptorDelegate {

    private static final String TAG = BundleExtrasInterceptorImpl.class.getSimpleName();

    private BundleExtrasInterceptor mCallbacks;

    public BundleExtrasInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks = (BundleExtrasInterceptor)activity;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        Bundle extras = getActivity().getIntent() != null ? getActivity().getIntent().getExtras() : null;
        mCallbacks.onHandleExtras(savedInstanceState, extras);
    }

}
