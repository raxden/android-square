package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

interface AutoInflateLayoutInterceptor : Interceptor {

    fun setLayoutId(layoutId: Int)

}
