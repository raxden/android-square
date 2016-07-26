package com.raxdenstudios.square.application.interceptor.impl;

import android.app.Application;

import com.raxdenstudios.square.application.interceptor.TimberInterceptor;
import com.raxdenstudios.square.application.interceptor.callback.TimberInterceptorCallback;
import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationImpl;

import timber.log.Timber;

/**
 * Created by Raxden on 24/07/2016.
 */
public class TimberInterceptorImpl extends InterceptorApplicationImpl implements TimberInterceptorCallback {

    private static final String TAG = TimberInterceptorImpl.class.getSimpleName();

    private TimberInterceptor mCallbacks;

    public TimberInterceptorImpl(Application application) {
        super(application);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (TimberInterceptor)application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.Tree tree = mCallbacks.onCreateTimberTree();
        if (tree != null) {
            Timber.plant(tree);
        }
    }

}
