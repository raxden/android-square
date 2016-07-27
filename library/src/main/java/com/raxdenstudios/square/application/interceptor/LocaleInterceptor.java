package com.raxdenstudios.square.application.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.application.interceptor.delegate.LocaleInterceptorDelegate;

/**
 * Created by agomez on 13/07/2015.
 */
public interface LocaleInterceptor extends Interceptor {

    void onInterceptorCreated(LocaleInterceptorDelegate delegate);

}
