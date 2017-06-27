package com.raxdenstudios.square.interceptor.commons.countback;

import com.raxdenstudios.square.interceptor.Interceptor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CountBackInterceptor extends Interceptor {

    void setMessage(String message);

    void setRetries(int retries);

}
