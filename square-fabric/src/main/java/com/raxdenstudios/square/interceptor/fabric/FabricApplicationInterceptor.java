package com.raxdenstudios.square.interceptor.fabric;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.raxdenstudios.square.interceptor.ApplicationInterceptor;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
public class FabricApplicationInterceptor
        extends ApplicationInterceptor<FabricInteractor, FabricInterceptorCallback>
        implements FabricInteractor {

    public FabricApplicationInterceptor(Application application, FabricInterceptorCallback callback) {
        super(application, callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(mApplication, new Crashlytics());
    }

}