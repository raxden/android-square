package com.raxdenstudios.square.interceptor.type.application.impl;

import android.app.Application;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.interceptor.callback.CalligraphyInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.CalligraphyInterceptorConfig;
import com.raxdenstudios.square.interceptor.type.ApplicationInterceptor;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
public class CalligraphyInterceptorImpl
        extends ApplicationInterceptor<CalligraphyInterceptorConfig, CalligraphyInterceptorCallback>
        implements CalligraphyInterceptorConfig {

    public CalligraphyInterceptorImpl(Application application) {
        super(application);
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
