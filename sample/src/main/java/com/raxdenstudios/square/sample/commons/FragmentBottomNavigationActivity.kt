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
import com.raxdenstudios.square.interceptor.commons.fragmentbottomnavigation.HasFragmentBottomNavigationInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor
import com.raxdenstudios.square.sample.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation_activity.*

class FragmentBottomNavigationActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasToolbarInterceptor,
        HasFragmentBottomNavigationInterceptor<Fragment> {

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

    // ======== HasFragmentBottomNavigationInterceptor =====================================================

    override fun onCreateBottomNavigationView(): BottomNavigationView = bottom_navigation_view

    override fun onBottomNavigationViewCreated(bottomNavigationView: BottomNavigationView) {
        mBottomNavigationView = bottomNavigationView
    }

    override fun onLoadFragmentContainer(): View = container_view

    override fun onCreateFragment(itemId: Int): Fragment = when (itemId) {
        R.id.navigation_home -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Home") })
        R.id.navigation_dashboard -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Dashboard") })
        R.id.navigation_notifications -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Notifications") })
        else -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Home") })
    }

    override fun onFragmentLoaded(itemId: Int, fragment: Fragment) {
        when (itemId) {
            R.id.navigation_home -> mFirstFragment = fragment
            R.id.navigation_dashboard -> mSecondFragment = fragment
            R.id.navigation_notifications -> mThirdFragment = fragment
        }
    }

    override fun onBottomNavigationItemSelected(itemId: Int) {
    }

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
        mToolbarInterceptor = interceptor as? ToolbarInterceptor
    }
}