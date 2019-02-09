package com.raxdenstudios.square.interceptor

interface HasInterceptor<TInterceptor: Interceptor> {

    fun onInterceptorCreated(interceptor: TInterceptor)
}