package com.raxdenstudios.square.interceptor.commons.injectfragmentlist

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InjectFragmentListActivityInterceptor<TFragment : Fragment>(
        callback: HasInjectFragmentListInterceptor<TFragment>
) : ActivityInterceptor<InjectFragmentListInterceptor, HasInjectFragmentListInterceptor<TFragment>>(callback),
        InjectFragmentListInterceptor {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)
        for (position in 0 until mCallback.fragmentCount)
            (activity as? FragmentActivity)?.let { initFragment(it, savedInstanceState, position) }?.let { mCallback.onFragmentLoaded(it, position) }
    }

    private fun initFragment(activity: FragmentActivity, savedInstanceState: Bundle?, position: Int): TFragment? {
        return mCallback.onLoadFragmentContainer(savedInstanceState, position).let { view ->
            savedInstanceState?.let {
                activity.supportFragmentManager.findFragmentById(view.id) as TFragment
            } ?: mCallback.onCreateFragment(position).let {
                activity.supportFragmentManager.beginTransaction().replace(view.id, it, it.javaClass.simpleName).commit()
                it
            }
        }
    }
}

