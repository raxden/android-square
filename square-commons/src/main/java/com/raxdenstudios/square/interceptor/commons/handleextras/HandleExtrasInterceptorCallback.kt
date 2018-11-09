package com.raxdenstudios.square.interceptor.commons.handleextras

import android.os.Bundle

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
interface HandleExtrasInterceptorCallback : InterceptorCallback {

    fun onHandleExtras(savedInstanceState: Bundle?, extras: Bundle)

}
