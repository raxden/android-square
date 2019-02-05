package com.raxdenstudios.square.interceptor

interface HasInterceptor<Int: Interceptor> {

    fun onInterceptorAttached(interceptor: Int)

}