package com.raxdenstudios.square.interceptor.commons.toolbar

import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by agomez on 21/05/2015.
 */
interface ToolbarInterceptorCallback : InterceptorCallback {

    fun onCreateToolbarView(savedInstanceState: Bundle?): Toolbar?

    fun onToolbarViewCreated(toolbar: Toolbar)

}
