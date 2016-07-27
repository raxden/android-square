package com.raxdenstudios.square.application.interceptor.impl;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.raxdenstudios.square.application.interceptor.FabricInterceptor;
import com.raxdenstudios.square.application.interceptor.delegate.FabricInterceptorDelegate;
import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationImpl;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Raxden on 27/07/2016.
 */
public class FabricInterceptorImpl extends InterceptorApplicationImpl implements FabricInterceptorDelegate {

    private FabricInterceptor mCallbacks;

    public FabricInterceptorImpl(Application application) {
        super(application);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (FabricInterceptor)application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(getContext(), new Crashlytics());
    }

}
