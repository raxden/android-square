package com.raxdenstudios.square.sample

import android.app.Application
import com.raxdenstudios.square.InterceptorManager
import com.raxdenstudios.square.interceptor.commons.InterceptorCommonsFactory

/**
 * Created by Ángel Gómez on 12/06/2017.
 */

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val builder = InterceptorManager.Builder()
                .addInterceptorFactory(InterceptorCommonsFactory())
                .build()
        builder.init(this)
    }
}

