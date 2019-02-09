package com.raxdenstudios.square

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View

class InterceptorManager private constructor(builder: Builder) {

    private var factoryList = mutableListOf<InterceptorFactory>()

    init {
        factoryList = builder.factoryList
    }

    class Builder {

        val factoryList = mutableListOf<InterceptorFactory>()

        fun addInterceptorFactory(factory: InterceptorFactory): Builder {
            factoryList.add(factory)
            return this
        }

        fun build(): InterceptorManager = InterceptorManager(this)
    }

    fun init(app: Application) {
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                for (factory in factoryList) factory.onActivityCreated(activity, savedInstanceState)
            }

            override fun onActivityPaused(activity: Activity?) {
                for (factory in factoryList) factory.onActivityPaused(activity)
            }

            override fun onActivityResumed(activity: Activity?) {
                for (factory in factoryList) factory.onActivityResumed(activity)
            }

            override fun onActivityStarted(activity: Activity?) {
                for (factory in factoryList) factory.onActivityStarted(activity)
            }

            override fun onActivityDestroyed(activity: Activity?) {
                for (factory in factoryList) factory.onActivityDestroyed(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                for (factory in factoryList) factory.onActivitySaveInstanceState(activity, outState)
            }

            override fun onActivityStopped(activity: Activity?) {
                for (factory in factoryList) factory.onActivityStopped(activity)
            }
        })
    }
}