package com.raxdenstudios.square.sample.commons

import android.os.Bundle
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
        HasInjectFragmentInterceptor<InjectedFragment> {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null
    private var mInjectFragmentInterceptor: InjectFragmentInterceptor? = null

    var mContentView: View? = null
    var mInjectedFragment: InjectedFragment? = null

    // ======== HasInflateLayoutInterceptor ====================================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== HasFloatingActionButtonFragmentInterceptor =======================================================

    override fun onLoadFragmentContainer(): View = container_view

    override fun onCreateFragment(): InjectedFragment = InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 1") })

    override fun onFragmentLoaded(fragment: InjectedFragment) {
        mInjectedFragment = fragment
    }

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
        mInjectFragmentInterceptor = interceptor as? InjectFragmentInterceptor
    }
}