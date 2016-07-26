package com.raxdenstudios.square.application.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.application.interceptor.callback.TimberInterceptorCallback;

import timber.log.Timber;

/**
 * Created by Raxden on 24/07/2016.
 */
public interface TimberInterceptor extends Interceptor {

    void onInterceptorCreated(TimberInterceptorCallback callback);

    Timber.Tree onCreateTimberTree();

}
