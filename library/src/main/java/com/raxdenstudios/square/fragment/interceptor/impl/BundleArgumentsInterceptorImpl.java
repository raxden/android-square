package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.square.fragment.InterceptorFragment;
import com.raxdenstudios.square.fragment.interceptor.BundleArgumentsInterceptor;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleArgumentsInterceptorImpl extends InterceptorFragmentImpl implements BundleArgumentsInterceptor.BundleArgumentsInterceptorCallback {

    private static final String TAG = BundleArgumentsInterceptorImpl.class.getSimpleName();

    private BundleArgumentsInterceptor mCallbacks;

    public BundleArgumentsInterceptorImpl(Fragment fragment) {
        if (!(fragment instanceof BundleArgumentsInterceptor)) {
            throw new IllegalStateException("Fragment must implement BundleArgumentsInterceptor.");
        }
        mCallbacks = (BundleArgumentsInterceptor)fragment;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);

        Bundle arguments = null;
        if (mCallbacks instanceof InterceptorFragment) {
            arguments = ((InterceptorFragment)mCallbacks).getArguments();
        }

        if (mCallbacks != null) {
            mCallbacks.onHandleArguments(savedInstanceState, arguments);
        }
    }

}
