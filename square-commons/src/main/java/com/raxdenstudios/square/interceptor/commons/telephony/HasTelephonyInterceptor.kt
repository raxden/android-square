package com.raxdenstudios.square.interceptor.commons.telephony

import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 30/12/2016.
 */

interface HasTelephonyInterceptor : HasInterceptor {

    fun onCallStateChanged(state: Int, incomingNumber: String)
}
