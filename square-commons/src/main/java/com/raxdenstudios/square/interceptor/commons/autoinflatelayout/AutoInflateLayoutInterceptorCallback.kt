package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import android.os.Bundle
import android.view.View

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
interface AutoInflateLayoutInterceptorCallback : InterceptorCallback {

    fun onContentViewCreated(view: View, savedInstanceState: Bundle?)

}
