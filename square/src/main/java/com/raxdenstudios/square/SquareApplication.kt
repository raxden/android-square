package com.raxdenstudios.square

import android.app.Application
import android.content.res.Configuration
import com.raxdenstudios.square.interceptor.ApplicationInterceptor
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.manager.ApplicationInterceptorManager
import com.raxdenstudios.square.manager.InterceptorManagerFactory

/**
 * Created by Ángel Gómez
 *
 *
 * SquareApplication is an abstract class that adds interceptor functionality to the applicationInterceptorManager.
 */
abstract class SquareApplication : Application() {

    private var applicationInterceptorManager: ApplicationInterceptorManager? = null

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        interceptorManager.onConfigurationChanged(newConfig)
    }

    override fun onCreate() {
        super.onCreate()
        interceptorManager.onCreate()
    }

    /* Support methods */

    protected abstract fun setupInterceptors(interceptorList: MutableList<Interceptor>)

    private val interceptorManager: ApplicationInterceptorManager
        get() {
            return applicationInterceptorManager ?: initInterceptorManager()
        }

    private fun initInterceptorManager(): ApplicationInterceptorManager {
        applicationInterceptorManager = InterceptorManagerFactory.buildManager(this) as ApplicationInterceptorManager
        applicationInterceptorManager?.apply {
            val interceptorList = mutableListOf<Interceptor>()
            setupInterceptors(interceptorList)
            interceptorList.forEach { interceptor -> addInterceptor((interceptor as ApplicationInterceptor<*>)) }
        }
        return applicationInterceptorManager!!
    }

}
