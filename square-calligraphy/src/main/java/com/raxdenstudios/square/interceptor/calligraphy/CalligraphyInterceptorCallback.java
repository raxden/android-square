package com.raxdenstudios.square.interceptor.calligraphy;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface CalligraphyInterceptorCallback
        extends InterceptorCallback<CalligraphyInteractor> {

    String onLoadDefaultFontPath();

}
