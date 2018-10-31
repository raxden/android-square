package com.raxdenstudios.square.manager

import android.app.Application
import android.content.res.Configuration

import com.raxdenstudios.square.interceptor.ApplicationInterceptor
import com.raxdenstudios.square.lifecycle.ApplicationLifecycle

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

class ApplicationInterceptorManager(application: Application) : InterceptorManager<Application, ApplicationInterceptor<*>>(application), ApplicationLifecycle {

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
