package com.raxdenstudios.square.interceptor;

/**
 * Created by Ángel Gómez
 *
 * Interceptor callback
 */
public interface InterceptorCallback<TInteractor extends InterceptorInteractor> {

    void onInterceptorAttached(TInteractor interceptor);

}
