package com.raxdenstudios.square.interceptor.commons.autoinflateview


import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by agomez on 02/06/2015.
 */
interface AutoInflateViewInterceptor : Interceptor {

    fun setLayoutId(layoutId: Int)

}
