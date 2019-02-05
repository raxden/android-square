package com.raxdenstudios.square.sample

import android.app.Application
import com.raxdenstudios.square.InterceptorManager

/**
 * Created by Ángel Gómez on 12/06/2017.
 */

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        InterceptorManager.init(this)
    }
}
