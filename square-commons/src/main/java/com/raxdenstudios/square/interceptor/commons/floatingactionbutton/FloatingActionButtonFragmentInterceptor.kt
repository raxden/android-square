package com.raxdenstudios.square.interceptor.commons.floatingactionbutton

import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

interface FloatingActionButtonFragmentInterceptor : Interceptor {
    fun setNavigationIcon(icon: Int)
    fun setStartColorAnimation(color: Int)
    fun setEndColorAnimation(color: Int)
    fun setDurationAnimation(duration: Int)
}
