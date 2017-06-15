package com.raxdenstudios.square.interceptor.calligraphy;

import android.app.Application;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
public class CalligraphyApplicationInterceptorImpl extends ApplicationInterceptor<CalligraphyInterceptorCallback> implements CalligraphyInterceptor {

    public CalligraphyApplicationInterceptorImpl(Application application) {
        super(application);
    }

    public CalligraphyApplicationInterceptorImpl(Application application, CalligraphyInterceptorCallback callback) {
        super(application, callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(mCallback.onLoadDefaultFontPath())
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
