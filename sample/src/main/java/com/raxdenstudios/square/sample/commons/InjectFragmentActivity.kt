package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback
import kotlinx.android.synthetic.main.inject_fragment_activity.*

class InjectFragmentActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptorCallback,
        InjectFragmentInterceptorCallback<InjectFragmentActivity.InjectedFragment> {

    var mContentView: View? = null
    var mInjectedFragment: InjectedFragment? = null

    // ======== AutoInflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== InjectFragmentInterceptorCallback ===============================================

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = container_view

    override fun onCreateFragment(): InjectedFragment = InjectedFragment.newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: InjectedFragment) {
        mInjectedFragment = fragment
    }

    // ======== SUPPORT METHODS ====================================================================

    override fun setupInterceptors(interceptorList: MutableList<ActivityInterceptor<*>>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
        interceptorList.add(InjectFragmentActivityInterceptor(this, this))
    }

    class InjectedFragment: Fragment() {

        companion object {
            fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }
    }

}