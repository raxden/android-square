package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.BundleExtrasInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.BundleExtrasInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleExtrasInterceptorImpl extends InterceptorActivityImpl<BundleExtrasInterceptor>
        implements BundleExtrasInterceptorCallback {

    private static final String TAG = BundleExtrasInterceptorImpl.class.getSimpleName();

    public BundleExtrasInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        Bundle extras = mActivity.getIntent() != null ? mActivity.getIntent().getExtras() : null;
        mCallbacks.onHandleExtras(savedInstanceState, extras);
    }

}
