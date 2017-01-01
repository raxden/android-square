package com.raxdenstudios.square.interceptor.interactor;

import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface TimerInterceptorInteractor extends InterceptorInteractor {

    void setTime(long milliseconds);

    void setTime(int seconds);

}
