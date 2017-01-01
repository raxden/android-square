package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.TimerInterceptorInteractor;

/**
 * Created by agomez on 11/05/2015.
 */
public interface TimerInterceptorCallback
        extends InterceptorCallback<TimerInterceptorInteractor> {

    void onTimerEnd();

    void onTimerCancelled();

}
