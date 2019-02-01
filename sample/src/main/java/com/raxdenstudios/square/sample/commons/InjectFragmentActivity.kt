package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.SquareFragment
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback
import kotlinx.android.synthetic.main.inject_fragment_activity.*

class InjectFragmentActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptorCallback,
        InjectFragmentInterceptorCallback<InjectFragmentActivity.InjectedFragment> {

    var mContentView: View? = null
    var mInjectedFragment: InjectedFragment? = null

    // ======== InflateLayoutInterceptorCallback ===============================================

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

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
        interceptorList.add(InjectFragmentActivityInterceptor(this, this))
    }

    class InjectedFragment
        : SquareFragment(),
            AutoInflateViewInterceptorCallback {

        companion object {
            fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }

        override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
            interceptorList.add(AutoInflateViewFragmentInterceptor(this, this))
        }
    }

}