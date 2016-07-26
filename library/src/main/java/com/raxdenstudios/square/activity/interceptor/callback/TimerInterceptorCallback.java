package com.raxdenstudios.square.activity.interceptor.callback;

import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface TimerInterceptorCallback extends InterceptorCallback {

    void setTimer(int seconds);

    void setTimer(long miliseconds);

}
