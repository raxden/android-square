package com.raxdenstudios.square.interceptor

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

abstract class ActivityInterceptor<TInterceptor : Interceptor, TCallback : HasInterceptor>(
        val mCallback: TCallback
) : Application.ActivityLifecycleCallbacks, Interceptor {

    var mSavedInstanceState: Bundle? = null

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        mCallback.onInterceptorCreated(this as TInterceptor)
        mSavedInstanceState = savedInstanceState
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
        mSavedInstanceState = null
    }

    protected fun getFragmentManager(activity: Activity?) : FragmentManager? = (activity as? FragmentActivity)?.supportFragmentManager
}