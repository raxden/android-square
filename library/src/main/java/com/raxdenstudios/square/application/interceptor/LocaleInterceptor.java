package com.raxdenstudios.square.application.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.application.interceptor.callback.LocaleInterceptorCallback;

/**
 * Created by agomez on 13/07/2015.
 */
public interface LocaleInterceptor extends Interceptor {

    void onInterceptorCreated(LocaleInterceptorCallback callback);

}
