package com.raxdenstudios.square.interceptor.calligraphy;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.Interactor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class CalligraphyActivityInterceptor
        extends ActivityInterceptor<Interactor, InterceptorCallback<Interactor>>
        implements Interactor {

    public CalligraphyActivityInterceptor(@NonNull Activity activity, @NonNull InterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        newBase = CalligraphyContextWrapper.wrap(newBase);
    }

}

