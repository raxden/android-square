package com.raxdenstudios.square.interceptor.type.application.impl;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.raxdenstudios.square.interceptor.type.ApplicationInterceptor;
import com.raxdenstudios.square.interceptor.callback.FabricInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.FabricInterceptorConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
public class FabricInterceptorImpl
        extends ApplicationInterceptor<FabricInterceptorConfig, FabricInterceptorCallback>
        implements FabricInterceptorConfig {

    public FabricInterceptorImpl(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(mApplication, new Crashlytics());
    }

}
