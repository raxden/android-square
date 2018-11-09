package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
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
        initFragment(savedInstanceState)?.let { callback?.onFragmentLoaded(it) }
    }

    private fun initFragment(savedInstanceState: Bundle?): TFragment? =
        callback?.onLoadFragmentContainer(savedInstanceState)?.let { view ->
            savedInstanceState?.let { loadCurrentFragmentFromView(view) } ?: loadFragment(view)
        }

    private fun loadCurrentFragmentFromView(view: View): TFragment? =
            FragmentUtils.getFragment(activity.supportFragmentManager, view.id)

    private fun loadFragment(view: View): TFragment? =
            callback?.onCreateFragment()?.let {
                FragmentUtils.loadFragment(activity.supportFragmentManager, view.id, it)
                it
            }

}
