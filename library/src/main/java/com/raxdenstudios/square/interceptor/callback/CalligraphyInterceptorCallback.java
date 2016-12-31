package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.CalligraphyInterceptorConfig;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface CalligraphyInterceptorCallback
        extends InterceptorCallback<CalligraphyInterceptorConfig> {

    String onLoadDefaultFontPath();

}
