package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.TimerInterceptorConfig;

/**
 * Created by agomez on 11/05/2015.
 */
public interface TimerInterceptorCallback
        extends InterceptorCallback<TimerInterceptorConfig> {

    void onTimerEnd();

    void onTimerCancelled();

}
