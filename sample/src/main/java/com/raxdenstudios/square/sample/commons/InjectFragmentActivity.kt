package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import kotlinx.android.synthetic.main.inject_fragment_activity.*

class InjectFragmentActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasInjectFragmentInterceptor<InjectFragmentActivity.InjectedFragment> {

    lateinit var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor
    lateinit var mInjectFragmentInterceptor: InjectFragmentInterceptor

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

    // ==========================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as AutoInflateLayoutInterceptor
        mInjectFragmentInterceptor = interceptor as InjectFragmentInterceptor
    }

    class InjectedFragment : Fragment() {

        companion object {
            fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }
    }

}