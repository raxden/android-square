package com.raxdenstudios.square.fragment.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.fragment.interceptor.delegate.AutoInflateViewInterceptorDelegate;

/**
 * Created by agomez on 02/06/2015.
 */
public interface AutoInflateViewInterceptor extends Interceptor {

    void onInterceptorCreated(AutoInflateViewInterceptorDelegate callback);

}
