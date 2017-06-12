package com.raxdenstudios.square.interceptor.calligraphy;

import android.app.Application;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
public class CalligraphyApplicationInterceptor
        extends ApplicationInterceptor<CalligraphyInteractor, CalligraphyInterceptorCallback>
        implements CalligraphyInteractor {

    public CalligraphyApplicationInterceptor(Application application) {
        super(application);
    }

    public CalligraphyApplicationInterceptor(Application application, CalligraphyInterceptorCallback callback) {
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
