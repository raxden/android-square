package com.raxdenstudios.square.application.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.application.interceptor.delegate.TimberInterceptorDelegate;

import timber.log.Timber;

/**
 * Created by Raxden on 24/07/2016.
 */
public interface TimberInterceptor extends Interceptor {

    void onInterceptorCreated(TimberInterceptorDelegate callback);

    Timber.Tree onCreateTimberTree();

}
