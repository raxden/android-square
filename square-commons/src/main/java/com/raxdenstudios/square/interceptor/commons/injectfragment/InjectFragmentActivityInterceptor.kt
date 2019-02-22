package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InjectFragmentActivityInterceptor<TFragment : Fragment>(
        callback: HasInjectFragmentInterceptor<TFragment>
) : ActivityInterceptor<InjectFragmentInterceptor, HasInjectFragmentInterceptor<TFragment>>(callback),
        InjectFragmentInterceptor {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)
        (activity as? FragmentActivity)?.let { initFragment(it, savedInstanceState) }?.let { mCallback.onFragmentLoaded(it) }
    }

    private fun initFragment(activity: FragmentActivity, savedInstanceState: Bundle?): TFragment? {
        return mCallback.onLoadFragmentContainer().let { view ->
            savedInstanceState?.let {
                activity.supportFragmentManager.findFragmentById(view.id) as TFragment
            } ?: mCallback.onCreateFragment().also {
                activity.supportFragmentManager.beginTransaction().replace(view.id, it, it.javaClass.simpleName).commit()
            }
        }
    }
}

