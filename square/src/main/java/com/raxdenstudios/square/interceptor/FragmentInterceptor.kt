package com.raxdenstudios.square.interceptor

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View

abstract class FragmentInterceptor<TInterceptor : Interceptor, TCallback : HasInterceptor>(
        val mCallback: TCallback
) : FragmentManager.FragmentLifecycleCallbacks(), Interceptor {

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

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        mCallback.onInterceptorCreated(this as TInterceptor)
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
}