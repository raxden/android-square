package com.raxdenstudios.square.sample

import com.raxdenstudios.square.SquareApplication
import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 12/06/2017.
 */

open class AppApplication : SquareApplication() {

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
    }

}
