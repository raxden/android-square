package com.raxdenstudios.square.interceptor.calligraphy;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class CalligraphyActivityInterceptorImpl extends ActivityInterceptor<CalligraphyInterceptorCallback> implements CalligraphyInterceptor {

    public CalligraphyActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public CalligraphyActivityInterceptorImpl(@NonNull Activity activity, @NonNull CalligraphyInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        newBase = CalligraphyContextWrapper.wrap(newBase);
    }

}

