package com.raxdenstudios.square.sample;

import android.app.Application;

import com.raxdenstudios.square.interceptor.ApplicationSimpleInterceptor;

/**
 * Created by Ángel Gómez on 12/06/2017.
 */

public class CustomInterceptor extends ApplicationSimpleInterceptor {

    public CustomInterceptor(Application application) {
        super(application);
    }
}
