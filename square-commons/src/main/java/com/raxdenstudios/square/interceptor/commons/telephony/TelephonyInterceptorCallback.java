package com.raxdenstudios.square.interceptor.commons.telephony;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 30/12/2016.
 */

public interface TelephonyInterceptorCallback extends InterceptorCallback {

    void onCallStateChanged(int state, String incomingNumber);

}
