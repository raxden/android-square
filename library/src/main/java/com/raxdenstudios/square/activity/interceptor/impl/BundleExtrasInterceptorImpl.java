package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.BundleExtrasInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleExtrasInterceptorImpl extends InterceptorActivityImpl implements BundleExtrasInterceptor.BundleExtrasInterceptorCallback {

    private static final String TAG = BundleExtrasInterceptorImpl.class.getSimpleName();

    private BundleExtrasInterceptor mCallbacks;

    public BundleExtrasInterceptorImpl(Activity activity) {
        super(activity);
        if (!(activity instanceof BundleExtrasInterceptor)) {
            throw new IllegalStateException("Activity must implement BundleExtrasInterceptor.");
        }
        mCallbacks = (BundleExtrasInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        if (mCallbacks != null) {
            mCallbacks.onHandleExtras(savedInstanceState, mActivity.getIntent().getExtras());
        }
    }

}
