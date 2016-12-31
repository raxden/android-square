package com.raxdenstudios.square.interceptor.type.application.impl;

import android.app.Application;

import com.raxdenstudios.square.interceptor.type.ApplicationInterceptor;
import com.raxdenstudios.square.interceptor.callback.TimberInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.TimberInterceptorConfig;

import timber.log.Timber;

/**
 * Created by Ángel Gómez on 24/07/2016.
 */
public class TimberInterceptorImpl
        extends ApplicationInterceptor<TimberInterceptorConfig, TimberInterceptorCallback>
        implements TimberInterceptorConfig {

    public TimberInterceptorImpl(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.Tree tree = mCallback.onCreateTimberTree();
        if (tree != null) {
            Timber.plant(tree);
        }
    }

}
