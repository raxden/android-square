package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
interface AutoInflateLayoutInterceptor : Interceptor {

    fun setLayoutId(layoutId: Int)
}
