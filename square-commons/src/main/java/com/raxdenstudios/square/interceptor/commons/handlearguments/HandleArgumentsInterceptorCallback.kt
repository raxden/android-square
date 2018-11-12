package com.raxdenstudios.square.interceptor.commons.handlearguments

import android.os.Bundle

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by agomez on 22/05/2015.
 */
interface HandleArgumentsInterceptorCallback : InterceptorCallback {

    fun onHandleArguments(savedInstanceState: Bundle?, arguments: Bundle)

}
