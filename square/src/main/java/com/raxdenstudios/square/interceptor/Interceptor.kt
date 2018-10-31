package com.raxdenstudios.square.interceptor

/**
 * Created by Ángel Gómez
 *
 * Contract that define a Interceptor
 */
interface Interceptor {
    fun onCreate()
    fun onDestroy()
}
