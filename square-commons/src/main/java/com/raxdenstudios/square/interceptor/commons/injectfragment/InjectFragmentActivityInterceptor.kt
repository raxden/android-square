package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InjectFragmentActivityInterceptor<TFragment : Fragment>(
        callback: HasInjectFragmentInterceptor<TFragment>
) : ActivityInterceptor<InjectFragmentInterceptor, HasInjectFragmentInterceptor<TFragment>>(callback),
        InjectFragmentInterceptor {

    private lateinit var mContainerView: View
    private var mHasSavedInstanceState: Boolean = false
    private var mFragment: TFragment? = null

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? FragmentActivity)?.also {
            mHasSavedInstanceState = savedInstanceState != null
            mContainerView = mCallback.onLoadFragmentContainer()
            mFragment = instantiateFragment(activity)
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        super.onActivityStarted(activity)

        (activity as? FragmentActivity)?.also {
            mFragment = instantiateFragment(activity)
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        super.onActivityDestroyed(activity)

        mFragment = null
    }

    private fun instantiateFragment(activity: FragmentActivity): TFragment? {
        return if (!mHasSavedInstanceState) {
            mCallback.onCreateFragment().also {
                activity.supportFragmentManager.beginTransaction().replace(mContainerView.id, it, it.javaClass.simpleName).commit()
                mCallback.onFragmentLoaded(it)
            }
        } else if (mFragment == null) {
            (activity.supportFragmentManager.findFragmentById(mContainerView.id) as? TFragment)?.also {
                mCallback.onFragmentLoaded(it)
            }
        } else {
            return mFragment
        }
    }
}

