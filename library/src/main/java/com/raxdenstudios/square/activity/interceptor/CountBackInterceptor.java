package com.raxdenstudios.square.activity.interceptor;

/**
 * Created by agomez on 06/05/2015.
 */
public interface CountBackInterceptor {

    void onInterceptorLoaded(CountBackInterceptorListener interceptor);

    interface CountBackInterceptorListener {

    }
}
