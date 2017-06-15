package com.raxdenstudios.square.interceptor.timer;

import com.raxdenstudios.square.interceptor.Interceptor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface TimerInterceptor extends Interceptor {

    void setTime(long milliseconds);

    void setTime(int seconds);

}
