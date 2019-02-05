package com.raxdenstudios.square.interceptor

import android.app.Activity
import android.app.Application
import android.os.Bundle

abstract class ActivityInterceptor<TCallback: HasInterceptor<Interceptor>>(
        val mCallback: TCallback
): Application.ActivityLifecycleCallbacks {

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }
}