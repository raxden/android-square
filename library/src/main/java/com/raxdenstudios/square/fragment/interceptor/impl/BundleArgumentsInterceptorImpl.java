package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.os.Bundle;

import com.raxdenstudios.square.fragment.interceptor.BundleArgumentsInterceptor;
import com.raxdenstudios.square.fragment.interceptor.callback.BundleArgumentsInterceptorCallback;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleArgumentsInterceptorImpl extends InterceptorFragmentImpl implements BundleArgumentsInterceptorCallback {

    private static final String TAG = BundleArgumentsInterceptorImpl.class.getSimpleName();

    private BundleArgumentsInterceptor mCallbacks;

    public BundleArgumentsInterceptorImpl(Fragment fragment) {
        super(fragment);

        mCallbacks = (BundleArgumentsInterceptor)fragment;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mCallbacks.onHandleArguments(savedInstanceState, getFragment().getArguments());
    }

}
