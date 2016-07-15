package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.BundleExtrasInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleExtrasInterceptorImpl extends InterceptorActivityImpl implements BundleExtrasInterceptor.BundleExtrasInterceptorCallback {

    private static final String TAG = BundleExtrasInterceptorImpl.class.getSimpleName();

    private BundleExtrasInterceptor mCallbacks;

    public BundleExtrasInterceptorImpl(InterceptorActivity activity) {
        if (!(activity instanceof BundleExtrasInterceptor)) {
            throw new IllegalStateException("Activity must implement BundleExtrasInterceptor.");
        }
        mCallbacks = (BundleExtrasInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);

        Bundle bundleExtras = null;
        if (context instanceof Activity) {
            bundleExtras = ((Activity)context).getIntent().getExtras();
        }

        if (mCallbacks != null) mCallbacks.onHandleExtras(savedInstanceState, bundleExtras);
    }

}
