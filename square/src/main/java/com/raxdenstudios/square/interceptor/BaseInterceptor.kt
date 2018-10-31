package com.raxdenstudios.square.interceptor

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log

/**
 * Created by Ángel Gómez
 *
 *
 * This abstract class defines the basis of an interceptor.
 */
abstract class BaseInterceptor<TCallback : InterceptorCallback>(
        protected var context: Context,
        protected var callback: TCallback? = null)
    : Interceptor {

    constructor(application: Application, callback: TCallback? = null) : this(application.applicationContext, callback)

    constructor(activity: Activity, callback: TCallback? = null) : this(activity as Context, callback)

    constructor(fragment: Fragment, callback: TCallback? = null) : this(fragment.activity as Context, callback)

    constructor(dialogFragment: DialogFragment, callback: TCallback? = null) : this(dialogFragment.activity as Context, callback)

    // =============================================================================================

    override fun onCreate() {
        Log.d(BaseInterceptor::class.java.simpleName, this.javaClass.simpleName + " created!")
    }

    override fun onDestroy() {
        Log.d(BaseInterceptor::class.java.simpleName, this.javaClass.simpleName + " destroyed!")
        callback = null
    }

}
