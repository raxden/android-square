package com.raxdenstudios.square.interceptor.commons.injectfragmentlist

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InjectFragmentListActivityInterceptor<TFragment : Fragment>(
        callback: HasInjectFragmentListInterceptor<TFragment>
) : ActivityInterceptor<InjectFragmentListInterceptor, HasInjectFragmentListInterceptor<TFragment>>(callback),
        InjectFragmentListInterceptor {

    private var mContainerViewMap: MutableMap<Int, View> = mutableMapOf()
    private var mHasSavedInstanceState: Boolean = false
    private var mContainerFragmentMap: MutableMap<Int, TFragment?> = mutableMapOf()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? FragmentActivity)?.also {
            mHasSavedInstanceState = savedInstanceState != null
            for (position in 0 until mCallback.fragmentCount) {
                mContainerViewMap[position] = mCallback.onLoadFragmentContainer(position)
                mContainerFragmentMap[position] = initFragment(activity, position)
            }
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        super.onActivityStarted(activity)

        (activity as? FragmentActivity)?.also {
            for (position in 0 until mCallback.fragmentCount) {
                mContainerFragmentMap[position] = initFragment(activity, position)
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        super.onActivityDestroyed(activity)

        mContainerFragmentMap.clear()
    }

    private fun initFragment(activity: FragmentActivity, position: Int): TFragment? {
        return mContainerViewMap[position]?.let { view ->
            if (!mHasSavedInstanceState) {
                mCallback.onCreateFragment(position).also {
                    activity.supportFragmentManager.beginTransaction().replace(view.id, it, it.javaClass.simpleName).commit()
                    mCallback.onFragmentLoaded(position, it)
                }
            } else if (mContainerFragmentMap[position] == null) {
                (activity.supportFragmentManager.findFragmentById(view.id) as? TFragment)?.also {
                    mCallback.onFragmentLoaded(position, it)
                }
            } else {
                mContainerFragmentMap[position]
            }
        }
    }
}

