package com.raxdenstudios.square.interceptor.type;

import android.app.Application;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public abstract class ApplicationSimpleInterceptor
        extends ApplicationInterceptor<InterceptorConfig, InterceptorCallback<InterceptorConfig>> {

    public ApplicationSimpleInterceptor(Application application) {
        super(application);
    }

}
