package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.TelephonyInterceptorConfig;

/**
 * Created by Ángel Gómez on 30/12/2016.
 */

public interface TelephonyInterceptorCallback
        extends InterceptorCallback<TelephonyInterceptorConfig> {

    void onCallStateChanged(int state, String incomingNumber);

}
