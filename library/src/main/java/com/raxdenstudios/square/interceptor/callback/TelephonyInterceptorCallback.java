package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.TelephonyInterceptorInteractor;

/**
 * Created by Ángel Gómez on 30/12/2016.
 */

public interface TelephonyInterceptorCallback
        extends InterceptorCallback<TelephonyInterceptorInteractor> {

    void onCallStateChanged(int state, String incomingNumber);

}
