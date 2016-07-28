package com.raxdenstudios.square.application.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.application.interceptor.delegate.JodaTimeInterceptorDelegate;

/**
 * Created by Raxden on 28/07/2016.
 */
public interface JodaTimeInterceptor extends Interceptor {

    void onInterceptorCreated(JodaTimeInterceptorDelegate delegate);

}
