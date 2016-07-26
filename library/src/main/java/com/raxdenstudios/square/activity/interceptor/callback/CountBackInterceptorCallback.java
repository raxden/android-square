package com.raxdenstudios.square.activity.interceptor.callback;

import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface CountBackInterceptorCallback extends InterceptorCallback {

    void setMessageBackToExit(String message);

    void setCountBackToExit(int countBack);

}
