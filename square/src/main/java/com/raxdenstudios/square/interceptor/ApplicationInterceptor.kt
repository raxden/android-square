package com.raxdenstudios.square.interceptor

import android.app.Application
import android.content.res.Configuration
import com.raxdenstudios.square.lifecycle.ApplicationLifecycle

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an application interceptor.
 */
abstract class ApplicationInterceptor<TCallback : InterceptorCallback>(
        protected var application: Application,
        callback: TCallback? = null)
    : BaseInterceptor<TCallback>(application, callback),
        ApplicationLifecycle {

    override fun onConfigurationChanged(newConfig: Configuration?) {}

    override fun onLowMemory() {}

    override fun onTrimMemory(level: Int) {}

}
