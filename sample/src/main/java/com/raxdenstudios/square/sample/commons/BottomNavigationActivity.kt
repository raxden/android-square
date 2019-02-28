package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.bottomnavigation.HasBottomNavigationInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor
import kotlinx.android.synthetic.main.bottom_navigation_activity.*

class BottomNavigationActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasToolbarInterceptor,
        HasBottomNavigationInterceptor<Fragment> {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null
    private var mToolbarInterceptor: ToolbarInterceptor? = null

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null
    var mBottomNavigationView: BottomNavigationView? = null

    var mFirstFragment: Fragment? = null
    var mSecondFragment: Fragment? = null
    var mThirdFragment: Fragment? = null

    // ======== HasInflateLayoutInterceptor ========================================================

    override fun onContentViewCreated(view: View) {
        mContentView = view
    }

    // ======== HasToolbarInterceptor ==============================================================

    override fun onCreateToolbarView(): Toolbar = toolbar_view

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        mToolbarView = toolbar
    }

    // ======== HasBottomNavigationInterceptor =====================================================

    override fun onCreateBottomNavigationView(): BottomNavigationView = bottom_navigation_view

    override fun onBottomNavigationViewCreated(bottomNavigationView: BottomNavigationView) {
        mBottomNavigationView = bottomNavigationView
    }

    override fun onLoadFragmentContainer(): View = container_view

    override fun onCreateFragment(position: Int): Fragment = when (position) {
        0 -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 1") })
        1 -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 2") })
        2 -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 3") })
        else -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 1") })
    }

    override fun onFragmentLoaded(position: Int, fragment: Fragment) {
        when (position) {
            0 -> mFirstFragment = fragment
            1 -> mSecondFragment = fragment
            2 -> mThirdFragment = fragment
        }
    }

    override fun onPageSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
        mToolbarInterceptor = interceptor as? ToolbarInterceptor
    }
}