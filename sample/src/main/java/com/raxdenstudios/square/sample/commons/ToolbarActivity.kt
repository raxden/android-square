package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor
import kotlinx.android.synthetic.main.toolbar_activity.*

class ToolbarActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasToolbarInterceptor,
        HasInjectFragmentInterceptor<InjectedFragment> {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null
    private var mToolbarInterceptor: ToolbarInterceptor? = null
    private var mInjectFragmentInterceptor: InjectFragmentInterceptor? = null

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null
    var mInjectedFragment: InjectedFragment? = null

    // ======== HasInflateLayoutInterceptor ====================================================

    override fun onContentViewCreated(view: View) {
        mContentView = view
    }

    // ======== HasToolbarInterceptor ==============================================================

    override fun onCreateToolbarView(): Toolbar = toolbar_view

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        mToolbarView = toolbar
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
        mToolbarInterceptor = interceptor as? ToolbarInterceptor
        mInjectFragmentInterceptor = interceptor as? InjectFragmentInterceptor
    }
}