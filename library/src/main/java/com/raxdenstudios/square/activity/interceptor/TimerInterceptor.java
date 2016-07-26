package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.callback.TimerInterceptorCallback;

/**
 * Created by agomez on 11/05/2015.
 */
public interface TimerInterceptor extends Interceptor<TimerInterceptorCallback> {

    void onTimerEnd();

    void onTimerCancelled();

}
