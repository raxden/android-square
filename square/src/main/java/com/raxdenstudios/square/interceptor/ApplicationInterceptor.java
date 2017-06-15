package com.raxdenstudios.square.interceptor;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.lifecycle.ApplicationLifecycle;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an application interceptor.
 */
public abstract class ApplicationInterceptor<TCallback extends InterceptorCallback> extends BaseInterceptor<TCallback> implements ApplicationLifecycle {

    protected Application mApplication;

    public ApplicationInterceptor(Application application) {
        super(application);
        mApplication = application;
    }

    public ApplicationInterceptor(Application application, TCallback callback) {
        super(application, callback);
        mApplication = application;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }

}
