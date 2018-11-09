package com.raxdenstudios.square.interceptor.commons.network

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by agomez on 08/05/2015.
 */
interface NetworkInterceptorCallback : InterceptorCallback {

    fun onWifiAvailable(available: Boolean)

    fun onNetworkAvailable(available: Boolean)

}
