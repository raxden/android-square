package com.raxdenstudios.square.interceptor;

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
public interface InterceptorCallback<TConfig extends InterceptorConfig> {

    void onInterceptorAttached(TConfig configuration);

}
