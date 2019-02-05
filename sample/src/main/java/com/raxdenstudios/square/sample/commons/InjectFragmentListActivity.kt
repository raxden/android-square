package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.SquareFragment
import com.raxdenstudios.square.interceptor.HasInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListInterceptorCallback

class InjectFragmentListActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptor,
        InjectFragmentListInterceptorCallback<InjectFragmentListActivity.InjectedFragment> {

    var mContentView: View? = null

    var mFirstFragment: Fragment? = null
    var mSecondFragment: Fragment? = null
    var mThirdFragment: Fragment? = null

    // ======== InflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== InjectFragmentListInterceptorCallback ===============================================

    override val fragmentCount: Int
        get() = 3

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?, position: Int): View? = when(position) {
        0 -> container_first_view
        1 -> container_second_view
        2 -> container_third_view
        else -> null
    }

    override fun onCreateFragment(position: Int): InjectedFragment? = when(position) {
        0 -> InjectedFragment.newInstance(intent.extras)
        1 -> InjectedFragment.newInstance(intent.extras)
        2 -> InjectedFragment.newInstance(intent.extras)
        else -> null
    }

    override fun onFragmentLoaded(fragment: InjectedFragment, position: Int) {
        when(position) {
            0 -> mFirstFragment = fragment
            1 -> mSecondFragment = fragment
            2 -> mThirdFragment = fragment
        }
    }

    // ======== SUPPORT METHODS ====================================================================

    override fun setupInterceptors(interceptorList: MutableList<HasInterceptor>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
        interceptorList.add(InjectFragmentListActivityInterceptor(this, this))
    }

    class InjectedFragment
        : SquareFragment(),
            AutoInflateViewInterceptorCallback {

        companion object {
            fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }

        override fun setupInterceptors(interceptorList: MutableList<HasInterceptor>) {
            interceptorList.add(AutoInflateViewFragmentInterceptor(this, this))
        }
    }

}