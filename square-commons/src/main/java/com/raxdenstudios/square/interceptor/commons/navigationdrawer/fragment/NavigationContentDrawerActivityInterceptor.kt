package com.raxdenstudios.square.interceptor.commons.navigationdrawer.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.Gravity
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.base.NavigationDrawerActivityBaseInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
class NavigationContentDrawerActivityInterceptor<TFragment : Fragment>(
        callback: HasNavigationContentDrawerInterceptor<TFragment>)
    : NavigationDrawerActivityBaseInterceptor<NavigationContentDrawerInterceptor, HasNavigationContentDrawerInterceptor<TFragment>>(callback),
        NavigationContentDrawerInterceptor {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? FragmentActivity)?.apply {
            initFragment(this, Gravity.START, savedInstanceState)?.let { mCallback.onContentDrawerFragmentLoaded(Gravity.START, it) }
            initFragment(this, Gravity.END, savedInstanceState)?.let { mCallback.onContentDrawerFragmentLoaded(Gravity.END, it) }
        }
    }

    private fun initFragment(activity: FragmentActivity, gravity: Int, savedInstanceState: Bundle?): TFragment? {
        return mContentDrawerView[gravity]?.let { view ->
            savedInstanceState?.let {
                activity.supportFragmentManager.findFragmentById(view.id) as TFragment
            } ?: mCallback.onCreateContentDrawerFragment(gravity).let {
                activity.supportFragmentManager.beginTransaction().replace(view.id, it, it.javaClass.simpleName).commit()
                it
            }
        }
    }
}
