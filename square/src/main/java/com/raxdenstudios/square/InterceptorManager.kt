package com.raxdenstudios.square

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.View

object InterceptorManager {

    fun init(app: Application) {
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activity?.let { handleActivity(it) }
            }

            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {

            }

            override fun onActivityStarted(activity: Activity?) {

            }

            override fun onActivityDestroyed(activity: Activity?) {

            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {

            }
        })
    }

    private fun handleActivity(activity: Activity) {
        (activity as? FragmentActivity)?.run {
            supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {

                }

                override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {

                }

                override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {

                }

                override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {

                }

                override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {

                }

                override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {

                }

                override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {

                }

                override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {

                }

                override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {

                }

                override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {

                }

                override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {

                }

                override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {

                }

                override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {

                }

                override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {

                }
            }, true)
        }
    }

}