package com.raxdenstudios.square.manager

import android.content.res.Configuration
import android.support.multidex.MultiDexApplication

import com.raxdenstudios.square.interceptor.ApplicationInterceptor
import com.raxdenstudios.square.lifecycle.ApplicationLifecycle

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
class ApplicationMultiDexInterceptorManager(application: MultiDexApplication) : InterceptorManager<MultiDexApplication, ApplicationInterceptor<*>>(application), ApplicationLifecycle {

    override fun onConfigurationChanged(newConfig: Configuration?) {
        interceptors.forEach { it.onConfigurationChanged(newConfig) }
    }

    override fun onCreate() {
        interceptors.forEach { it.onCreate() }
    }

    override fun onLowMemory() {
        interceptors.forEach { it.onLowMemory() }
    }

    override fun onTrimMemory(level: Int) {
        interceptors.forEach { it.onTrimMemory(level) }
    }

}
