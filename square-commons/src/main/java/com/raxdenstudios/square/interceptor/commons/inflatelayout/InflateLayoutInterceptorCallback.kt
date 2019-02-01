package com.raxdenstudios.square.interceptor.commons.inflatelayout

import android.os.Bundle
import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
interface InflateLayoutInterceptorCallback : InterceptorCallback {

    fun onLayoutIdLoaded(layoutId: Int, savedInstanceState: Bundle?)

}
