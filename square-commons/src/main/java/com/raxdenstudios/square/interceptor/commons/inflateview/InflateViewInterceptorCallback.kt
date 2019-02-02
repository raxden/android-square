package com.raxdenstudios.square.interceptor.commons.inflateview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by agomez on 02/06/2015.
 */
interface InflateViewInterceptorCallback : InterceptorCallback {
    fun onCreateView(inflater: LayoutInflater, layoutId: Int?, container: ViewGroup?, savedInstanceState: Bundle?): View?
}
