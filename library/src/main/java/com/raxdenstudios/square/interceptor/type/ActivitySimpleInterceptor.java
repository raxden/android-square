package com.raxdenstudios.square.interceptor.type;

import android.app.Activity;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;

/**
 * Created by Ángel Gómez on 31/12/2016.
 */

public abstract class ActivitySimpleInterceptor
        extends ActivityInterceptor<InterceptorConfig, InterceptorCallback<InterceptorConfig>> {

    public ActivitySimpleInterceptor(Activity activity) {
        super(activity);
    }

}
