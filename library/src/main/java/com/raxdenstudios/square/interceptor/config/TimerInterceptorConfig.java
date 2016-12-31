package com.raxdenstudios.square.interceptor.config;

import com.raxdenstudios.square.interceptor.InterceptorConfig;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface TimerInterceptorConfig extends InterceptorConfig {

    void setTime(long milliseconds);

    void setTime(int seconds);

}
