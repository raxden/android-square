package com.raxdenstudios.square.interceptor.commons.inflatelayout

import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasInflateLayoutInterceptor : HasInterceptor {

    fun onLayoutLoaded(layoutId: Int)
}
