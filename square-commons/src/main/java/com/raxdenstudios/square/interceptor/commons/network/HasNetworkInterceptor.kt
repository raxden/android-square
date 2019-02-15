package com.raxdenstudios.square.interceptor.commons.network

import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by agomez on 08/05/2015.
 */
interface HasNetworkInterceptor : HasInterceptor {

    fun onWifiAvailable(available: Boolean)

    fun onNetworkAvailable(available: Boolean)
}
