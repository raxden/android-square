package com.raxdenstudios.square.sample

import com.raxdenstudios.square.SquareApplication
import com.raxdenstudios.square.interceptor.ApplicationInterceptor

/**
 * Created by Ángel Gómez on 12/06/2017.
 */

class SampleApplication : SquareApplication() {

    override fun setupInterceptors(interceptorList: MutableList<ApplicationInterceptor<*>>) {
    }

}
