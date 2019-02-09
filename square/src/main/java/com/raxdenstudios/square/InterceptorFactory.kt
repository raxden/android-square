package com.raxdenstudios.square

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor

abstract class InterceptorFactory : Application.ActivityLifecycleCallbacks {

    private var activityInterceptorList = mutableListOf<Interceptor>()
    private var fragmentInterceptorList = mutableListOf<Interceptor>()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            initActivityInterceptors(activity, activityInterceptorList)
            for (interceptor in activityInterceptorList) {
                if (interceptor is Application.ActivityLifecycleCallbacks)
                    interceptor.onActivityCreated(activity, savedInstanceState)
            }
            (activity as FragmentActivity).run {
                supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {

                    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
                        initFragmentInterceptors(activity, fragmentInterceptorList)
                        for (interceptor in fragmentInterceptorList) {
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentPreAttached(fm, f, context)
                        }
                    }

                    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentAttached(fm, f, context)
                    }

                    override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentPreCreated(fm, f, savedInstanceState)
                    }

                    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentCreated(fm, f, savedInstanceState)
                    }

                    override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentViewCreated(fm, f, v, savedInstanceState)
                    }

                    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentActivityCreated(fm, f, savedInstanceState)
                    }

                    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentStarted(fm, f)
                    }


                    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentResumed(fm, f)
                    }

                    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentPaused(fm, f)
                    }

                    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentStopped(fm, f)
                    }

                    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentViewDestroyed(fm, f)
                    }

                    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentDestroyed(fm, f)
                    }

                    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentDetached(fm, f)
                        destroyFragmentInterceptors()
                    }

                    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
                        for (interceptor in fragmentInterceptorList)
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentSaveInstanceState(fm, f, outState)
                    }
                }, true)
            }
        }
    }

    override fun onActivityPaused(activity: Activity?) {
        for (interceptor in activityInterceptorList)
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityPaused(activity)
    }

    override fun onActivityResumed(activity: Activity?) {
        for (interceptor in activityInterceptorList)
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityResumed(activity)
    }

    override fun onActivityStarted(activity: Activity?) {
        for (interceptor in activityInterceptorList)
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityStarted(activity)
    }

    override fun onActivityDestroyed(activity: Activity?) {
        for (interceptor in activityInterceptorList)
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityDestroyed(activity)
        destroyActivityInterceptors()
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        for (interceptor in activityInterceptorList)
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivitySaveInstanceState(activity, outState)
    }

    override fun onActivityStopped(activity: Activity?) {
        for (interceptor in activityInterceptorList)
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityStopped(activity)
    }

    abstract fun initActivityInterceptors(activity: Activity, list: MutableList<Interceptor>)

    abstract fun initFragmentInterceptors(fragment: FragmentActivity, list: MutableList<Interceptor>)

    private fun destroyActivityInterceptors() {
        activityInterceptorList.clear()
    }

    private fun destroyFragmentInterceptors() {
        fragmentInterceptorList.clear()
    }
}