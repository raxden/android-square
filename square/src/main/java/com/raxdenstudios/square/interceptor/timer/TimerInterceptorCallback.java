package com.raxdenstudios.square.interceptor.timer;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 11/05/2015.
 */
public interface TimerInterceptorCallback extends InterceptorCallback {

    void onTimerEnd();

    void onTimerCancelled();

}
