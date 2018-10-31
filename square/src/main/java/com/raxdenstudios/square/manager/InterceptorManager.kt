package com.raxdenstudios.square.manager

import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 15/07/2016.
 */
abstract class InterceptorManager<T, I : Interceptor>(protected var type: T) {

    protected val interceptors: MutableList<I> = mutableListOf()

    fun addInterceptor(interceptor: I) {
        interceptors.add(interceptor)
    }

}
