package com.raxdenstudios.square.interceptor.commons.navigationdrawer.injectfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.base.BaseNavigationDrawerActivityInterceptor
import com.raxdenstudios.square.utils.FragmentUtils


/**
 * Created by agomez on 21/05/2015.
 */
class NavigationContentDrawerActivityInterceptor<TFragment : Fragment>(
        private val appCompatActivity: AppCompatActivity,
        callback: NavigationContentDrawerInterceptorCallback<TFragment>)
    : BaseNavigationDrawerActivityInterceptor<NavigationContentDrawerInterceptorCallback<TFragment>>(appCompatActivity, callback),
        NavigationContentDrawerInterceptor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Gravity.START.let { gravity ->
            initFragment(savedInstanceState, gravity)?.let { callback?.onContentDrawerFragmentLoaded(gravity, it) }
        }
        Gravity.END.let { gravity ->
            initFragment(savedInstanceState, gravity)?.let { callback?.onContentDrawerFragmentLoaded(gravity, it) }
        }
    }

    private fun initFragment(savedInstanceState: Bundle?, gravity: Int): TFragment? =
            mContentDrawerView[gravity]?.let { view ->
                savedInstanceState?.let { loadCurrentFragmentFromView(view) }
                        ?: loadFragment(gravity, view)
            }

    private fun loadCurrentFragmentFromView(view: View): TFragment? =
            FragmentUtils.getFragment(activity.supportFragmentManager, view.id)

    private fun loadFragment(gravity: Int, view: View): TFragment? =
            callback?.onCreateContentDrawerFragment(gravity)?.let {
                FragmentUtils.loadFragment(activity.supportFragmentManager, view.id, it)
                it
            }

}
