package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.bottomnavigation.HasBottomNavigationInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor
import com.raxdenstudios.square.sample.R
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
        Log.d("TEST", "onContentViewCreated")
        mContentView = view
    }

    // ======== HasToolbarInterceptor ==============================================================

    override fun onCreateToolbarView(): Toolbar {
        Log.d("TEST", "onCreateToolbarView")
        return toolbar_view
    }

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        Log.d("TEST", "onToolbarViewCreated")
        mToolbarView = toolbar
    }

    // ======== HasBottomNavigationInterceptor =====================================================

    override fun onCreateBottomNavigationView(): BottomNavigationView {
        Log.d("TEST", "onCreateBottomNavigationView")
        return bottom_navigation_view
    }

    override fun onBottomNavigationViewCreated(bottomNavigationView: BottomNavigationView) {
        Log.d("TEST", "onBottomNavigationViewCreated")
        mBottomNavigationView = bottomNavigationView
    }

    override fun onLoadFragmentContainer(): View {
        Log.d("TEST", "onLoadFragmentContainer")
        return container_view
    }

    override fun onCreateFragment(itemId: Int): Fragment {
        Log.d("TEST", "onCreateFragment_$itemId")
        return when (itemId) {
            R.id.navigation_home -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Home") })
            R.id.navigation_dashboard -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Dashboard") })
            R.id.navigation_notifications -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Notifications") })
            else -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment Home") })
        }
    }

    override fun onFragmentLoaded(itemId: Int, fragment: Fragment) {
        Log.d("TEST", "onFragmentLoaded_$itemId $fragment")
        when (itemId) {
            R.id.navigation_home -> mFirstFragment = fragment
            R.id.navigation_dashboard -> mSecondFragment = fragment
            R.id.navigation_notifications -> mThirdFragment = fragment
        }
    }

    override fun onMenuItemSelected(itemId: Int) {
        Log.d("TEST", "onMenuItemSelected$itemId")
    }

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
        mToolbarInterceptor = interceptor as? ToolbarInterceptor
    }
}