package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by agomez on 06/05/2015.
 */
public interface CountBackInterceptor extends Interceptor<CountBackInterceptor.CountBackInterceptorCallback> {

    interface CountBackInterceptorCallback extends InterceptorCallback {

    }
}
