package com.raxdenstudios.square.fragment.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.fragment.interceptor.delegate.TimerInterceptorDelegate;

/**
 * Created by agomez on 11/05/2015.
 */
public interface TimerInterceptor extends Interceptor {

    void onInterceptorCreated(TimerInterceptorDelegate delegate);

    void onTimerEnd();

    void onTimerCancelled();

}
