package com.raxdenstudios.square.interceptor

import android.app.Activity
import android.app.Application
import android.os.Bundle

abstract class ActivityInterceptor<TInterceptor : Interceptor, TCallback : HasInterceptor<TInterceptor>>(
        val mCallback: TCallback
) : Application.ActivityLifecycleCallbacks, Interceptor {

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        mCallback.onInterceptorCreated(this as TInterceptor)
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