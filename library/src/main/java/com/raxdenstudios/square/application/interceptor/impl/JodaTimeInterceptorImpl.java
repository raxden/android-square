package com.raxdenstudios.square.application.interceptor.impl;

import android.app.Application;

import com.raxdenstudios.square.application.interceptor.JodaTimeInterceptor;
import com.raxdenstudios.square.application.interceptor.delegate.JodaTimeInterceptorDelegate;
import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationImpl;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Raxden on 28/07/2016.
 */
public class JodaTimeInterceptorImpl extends InterceptorApplicationImpl implements JodaTimeInterceptorDelegate {


    private JodaTimeInterceptor mCallbacks;

    public JodaTimeInterceptorImpl(Application application) {
        super(application);
        mCallbacks = (JodaTimeInterceptor)application;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(getApplication());
    }

}
