package com.raxdenstudios.square.interceptor.interactor;

import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CountBackInterceptorInteractor extends InterceptorInteractor {

    void setMessage(String message);

    void setRetries(int retries);

}
