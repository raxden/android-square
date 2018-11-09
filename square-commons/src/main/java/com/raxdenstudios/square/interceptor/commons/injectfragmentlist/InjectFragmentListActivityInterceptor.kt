package com.raxdenstudios.square.interceptor.commons.injectfragmentlist

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View

import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.utils.FragmentUtils

import java.util.ArrayList

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
class InjectFragmentListActivityInterceptor<TFragment : Fragment>(
        activity: FragmentActivity,
        callback: InjectFragmentListInterceptorCallback<TFragment>)
    : ActivityInterceptor<InjectFragmentListInterceptorCallback<TFragment>>(activity, callback),
        InjectFragmentListInterceptor {

    private val mContentFragmentList: MutableList<TFragment> = mutableListOf()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in mContentFragmentList) fragment.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        for (position in 0 until (callback?.fragmentCount ?: 0)) {
            initFragment(savedInstanceState, position)?.let {
                callback?.onFragmentLoaded(it, position)
                mContentFragmentList.add(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mContentFragmentList.clear()
    }

    private fun initFragment(savedInstanceState: Bundle?, position: Int): TFragment? =
            callback?.onLoadFragmentContainer(savedInstanceState, position)?.let { view ->
                savedInstanceState?.let { loadCurrentFragmentFromView(view) }
                        ?: loadFragment(view, position)
            }

    private fun loadCurrentFragmentFromView(view: View): TFragment? =
            FragmentUtils.getFragment(activity.supportFragmentManager, view.id)

    private fun loadFragment(view: View, position: Int): TFragment? =
            callback?.onCreateFragment(position)?.let {
                FragmentUtils.loadFragment(activity.supportFragmentManager, view.id, it)
                it
            }

}
