package com.raxdenstudios.square.lifecycle

import android.content.res.Configuration

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the Application life cycle used by interceptors.
 */
interface ApplicationLifecycle {

    fun onConfigurationChanged(newConfig: Configuration?)

    fun onCreate()

    fun onLowMemory()

    fun onTrimMemory(level: Int)

}
