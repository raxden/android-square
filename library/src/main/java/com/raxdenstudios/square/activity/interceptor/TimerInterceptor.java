package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.TimerInterceptorDelegate;

/**
 * Created by agomez on 11/05/2015.
 */
public interface TimerInterceptor extends Interceptor {

    void onInterceptorCreated(TimerInterceptorDelegate callback);

    void onTimerEnd();

    void onTimerCancelled();

}
