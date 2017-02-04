package com.raxdenstudios.square.interceptor;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.Interactor;
import com.raxdenstudios.square.lifecycle.ApplicationLifecycle;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an application interceptor.
 */
public abstract class ApplicationInterceptor<TInteractor extends Interactor, TCallback extends InterceptorCallback<TInteractor>>
        extends BaseInterceptor<TInteractor, TCallback>
        implements ApplicationLifecycle {

    protected Application mApplication;

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
