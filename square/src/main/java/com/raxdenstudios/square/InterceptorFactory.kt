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

    private var activityInterceptorList = mutableMapOf<Activity, MutableList<Interceptor>>()
    private var fragmentInterceptorList = mutableMapOf<Fragment, MutableList<Interceptor>>()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.also {
            activityInterceptorList[activity] = initActivityInterceptors(activity)
            activityInterceptorList[activity]?.forEach { interceptor ->
                if (interceptor is Application.ActivityLifecycleCallbacks)
                    interceptor.onActivityCreated(activity, savedInstanceState)
            }
            (activity as FragmentActivity).run {
                supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {

                    override fun onFragmentPreAttached(fm: FragmentManager, fragment: Fragment, context: Context) {
                        fragmentInterceptorList[fragment] = initFragmentInterceptors(fragment)
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentPreAttached(fm, fragment, context)
                        }
                    }

                    override fun onFragmentAttached(fm: FragmentManager, fragment: Fragment, context: Context) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentAttached(fm, fragment, context)
                        }
                    }

                    override fun onFragmentPreCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentPreCreated(fm, fragment, savedInstanceState)
                        }
                    }

                    override fun onFragmentCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentCreated(fm, fragment, savedInstanceState)
                        }
                    }

                    override fun onFragmentViewCreated(fm: FragmentManager, fragment: Fragment, v: View, savedInstanceState: Bundle?) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentViewCreated(fm, fragment, v, savedInstanceState)
                        }
                    }

                    override fun onFragmentActivityCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentActivityCreated(fm, fragment, savedInstanceState)
                        }
                    }

                    override fun onFragmentStarted(fm: FragmentManager, fragment: Fragment) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentStarted(fm, fragment)
                        }
                    }

                    override fun onFragmentResumed(fm: FragmentManager, fragment: Fragment) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentResumed(fm, fragment)
                        }
                    }

                    override fun onFragmentPaused(fm: FragmentManager, fragment: Fragment) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentPaused(fm, fragment)
                        }
                    }

                    override fun onFragmentStopped(fm: FragmentManager, fragment: Fragment) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentStopped(fm, fragment)
                        }
                    }

                    override fun onFragmentViewDestroyed(fm: FragmentManager, fragment: Fragment) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentViewDestroyed(fm, fragment)
                        }
                    }

                    override fun onFragmentDestroyed(fm: FragmentManager, fragment: Fragment) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentDestroyed(fm, fragment)
                        }
                    }

                    override fun onFragmentDetached(fm: FragmentManager, fragment: Fragment) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentDetached(fm, fragment)

                        }
                        fragmentInterceptorList[fragment]?.clear()
                        fragmentInterceptorList.remove(fragment)
                    }

                    override fun onFragmentSaveInstanceState(fm: FragmentManager, fragment: Fragment, outState: Bundle) {
                        fragmentInterceptorList[fragment]?.forEach { interceptor ->
                            if (interceptor is FragmentManager.FragmentLifecycleCallbacks)
                                interceptor.onFragmentSaveInstanceState(fm, fragment, outState)
                        }
                    }
                }, true)
            }
        }
    }

    override fun onActivityPaused(activity: Activity?) {
        activityInterceptorList[activity]?.forEach { interceptor ->
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityPaused(activity)
        }
    }

    override fun onActivityResumed(activity: Activity?) {
        activityInterceptorList[activity]?.forEach { interceptor ->
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityResumed(activity)
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        activityInterceptorList[activity]?.forEach { interceptor ->
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityStarted(activity)
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activityInterceptorList[activity]?.forEach { interceptor ->
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityDestroyed(activity)
        }
        activityInterceptorList[activity]?.clear()
        activityInterceptorList.remove(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        activityInterceptorList[activity]?.forEach { interceptor ->
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivitySaveInstanceState(activity, outState)
        }
    }

    override fun onActivityStopped(activity: Activity?) {
        activityInterceptorList[activity]?.forEach { interceptor ->
            if (interceptor is Application.ActivityLifecycleCallbacks)
                interceptor.onActivityStopped(activity)
        }
    }

    abstract fun initActivityInterceptors(activity: Activity): MutableList<Interceptor>

    abstract fun initFragmentInterceptors(fragment: Fragment): MutableList<Interceptor>
}