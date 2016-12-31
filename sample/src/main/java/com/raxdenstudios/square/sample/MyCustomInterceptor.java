package com.raxdenstudios.square.sample;

import android.app.Activity;

import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class MyCustomInterceptor
        extends ActivityInterceptor<MyCustomInterceptorConfig, MyCustomInterceptorCallback> {

    public MyCustomInterceptor(Activity activity) {
        super(activity);
    }

}
