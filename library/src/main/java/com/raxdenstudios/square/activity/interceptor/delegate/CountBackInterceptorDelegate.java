package com.raxdenstudios.square.activity.interceptor.delegate;

import com.raxdenstudios.square.InterceptorDelegate;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface CountBackInterceptorDelegate extends InterceptorDelegate {

    void setMessageBackToExit(String message);

    void setCountBackToExit(int countBack);

}
