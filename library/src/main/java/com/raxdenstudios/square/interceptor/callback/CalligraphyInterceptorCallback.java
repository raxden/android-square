package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.CalligraphyInterceptorInteractor;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface CalligraphyInterceptorCallback
        extends InterceptorCallback<CalligraphyInterceptorInteractor> {

    String onLoadDefaultFontPath();

}
