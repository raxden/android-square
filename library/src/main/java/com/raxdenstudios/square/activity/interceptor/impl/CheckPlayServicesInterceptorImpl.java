package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.activity.interceptor.CheckPlayServicesInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class CheckPlayServicesInterceptorImpl extends InterceptorActivityImpl implements CheckPlayServicesInterceptor.CheckPlayServicesInterceptorCallback {

    private static final String TAG = CheckPlayServicesInterceptorImpl.class.getSimpleName();

    private CheckPlayServicesInterceptor mCallbacks;

    public CheckPlayServicesInterceptorImpl(Activity activity) {
        if (!(activity instanceof CheckPlayServicesInterceptor)) {
            throw new IllegalStateException("Activity must implement CheckPlayServicesInterceptor.");
        }
        mCallbacks = (CheckPlayServicesInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle bundle) {
        super.onInterceptorCreate(context, bundle);
        if (!Utils.checkPlayServices(context)) {
            if (mCallbacks != null) mCallbacks.onGooglePlayServicesNotSupported();
        } else {
            if (mCallbacks != null) mCallbacks.onGooglePlayServicesSupported();
        }
    }

    @Override
    public void onInterceptorResume(Context context) {
        super.onInterceptorResume(context);
        if (!Utils.checkPlayServices(context)) {
            if (mCallbacks != null) mCallbacks.onGooglePlayServicesNotSupported();
        } else {
            if (mCallbacks != null) mCallbacks.onGooglePlayServicesSupported();
        }
    }
}
