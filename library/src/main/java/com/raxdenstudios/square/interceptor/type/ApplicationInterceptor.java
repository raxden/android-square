package com.raxdenstudios.square.interceptor.type;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;
import com.raxdenstudios.square.lifecycle.ApplicationLifecycle;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public abstract class ApplicationInterceptor<TConfig extends InterceptorConfig, TCallback extends InterceptorCallback<TConfig>>
        extends BaseInterceptor<TConfig, TCallback>
        implements ApplicationLifecycle {

    protected Application mApplication;

    public ApplicationInterceptor(Application application) {
        super(application);
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
