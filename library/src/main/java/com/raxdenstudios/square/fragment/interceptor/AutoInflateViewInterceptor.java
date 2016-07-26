package com.raxdenstudios.square.fragment.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.fragment.interceptor.callback.AutoInflateViewInterceptorCallback;

/**
 * Created by agomez on 02/06/2015.
 */
public interface AutoInflateViewInterceptor extends Interceptor {

    void onInterceptorCreated(AutoInflateViewInterceptorCallback callback);

}
