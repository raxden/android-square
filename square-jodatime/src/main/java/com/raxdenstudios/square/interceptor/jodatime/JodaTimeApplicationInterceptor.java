package com.raxdenstudios.square.interceptor.jodatime;

import android.app.Application;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Ángel Gómez on 28/07/2016.
 */
public class JodaTimeApplicationInterceptor
        extends ApplicationInterceptor<JodaTimeInteractor, JodaTimeInterceptorCallback>
        implements JodaTimeInteractor {

    public JodaTimeApplicationInterceptor(@NonNull Application application, @NonNull JodaTimeInterceptorCallback callback) {
        super(application, callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(mApplication);
    }

}
