package com.raxdenstudios.square.fragment.interceptor.delegate;

import com.raxdenstudios.square.InterceptorDelegate;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface TimerInterceptorDelegate extends InterceptorDelegate {

    void setTimer(int seconds);

    void setTimer(long miliseconds);

}
