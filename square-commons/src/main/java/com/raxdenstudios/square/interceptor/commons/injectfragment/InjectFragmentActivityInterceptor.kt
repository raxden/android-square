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
    private var mFragment: TFragment? = null

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            mContainerView = mCallback.onLoadFragmentContainer()
            if (savedInstanceState == null) {
                mFragment = mCallback.onCreateFragment().also {
                    fm.beginTransaction()
                            .replace(mContainerView.id, it, it.javaClass.simpleName)
                            .commit()
                    mCallback.onFragmentLoaded(it)
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityStarted(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            if (savedInstanceState != null) {
                mFragment = (fm.findFragmentById(mContainerView.id) as? TFragment)?.also {
                    mCallback.onFragmentLoaded(it)
                }
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        super.onActivityDestroyed(activity)
        mFragment = null
    }
}

