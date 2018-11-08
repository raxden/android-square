package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.utils.FragmentUtils

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
class InjectFragmentActivityInterceptor<TFragment : Fragment>(
        activity: FragmentActivity,
        callback: InjectFragmentInterceptorCallback<TFragment>)
    : ActivityInterceptor<InjectFragmentInterceptorCallback<TFragment>>(activity, callback),
        InjectFragmentInterceptor {

    private var mContentFragment: TFragment? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mContentFragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback?.onLoadFragmentContainer(savedInstanceState)?.let { view ->
            if (savedInstanceState != null) mContentFragment = FragmentUtils.getFragment(activity.supportFragmentManager, view.id) as TFragment
            else {
                mContentFragment = callback?.onCreateFragment()
                mContentFragment?.let { FragmentUtils.loadFragment(activity.supportFragmentManager, view.id, it) }
            }
            callback?.onFragmentLoaded(mContentFragment)
        }
    }


}
