package com.raxdenstudios.square

import android.content.res.Configuration
import android.support.multidex.MultiDexApplication
import com.raxdenstudios.square.interceptor.ApplicationInterceptor
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.manager.ApplicationMultiDexInterceptorManager
import com.raxdenstudios.square.manager.InterceptorManagerFactory

/**
 * Created by Ángel Gómez
 *
 * SquareMultiDexApplication is an abstract class that adds interceptor functionality to the
 * MultiDexApplication.
 */
abstract class SquareMultiDexApplication : MultiDexApplication() {

    private var applicationMultiDexInterceptorManager: ApplicationMultiDexInterceptorManager? = null

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

    private val interceptorManager: ApplicationMultiDexInterceptorManager
        get() {
            return applicationMultiDexInterceptorManager ?: initInterceptorManager()
        }

    private fun initInterceptorManager(): ApplicationMultiDexInterceptorManager {
        applicationMultiDexInterceptorManager = InterceptorManagerFactory.buildManager(this) as ApplicationMultiDexInterceptorManager
        applicationMultiDexInterceptorManager?.apply {
            val interceptorList = mutableListOf<Interceptor>()
            setupInterceptors(interceptorList)
            interceptorList.forEach { interceptor -> addInterceptor((interceptor as ApplicationInterceptor<*>)) }
        }
        return applicationMultiDexInterceptorManager!!
    }

}
