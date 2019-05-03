package com.raxdenstudios.square.sample.commons

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.Menu
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer.HasFragmentNavigationDrawerInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer.FragmentNavigationDrawerInterceptor
import kotlinx.android.synthetic.main.fragment_navigation_drawer_activity.*

class FragmentNavigationDrawerActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasInjectFragmentInterceptor<InjectedFragment>,
        HasFragmentNavigationDrawerInterceptor<InjectedFragment> {

    private lateinit var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor
    private lateinit var mInjectFragmentInterceptor: InjectFragmentInterceptor
    private lateinit var mFragmentNavigationDrawerInterceptor: FragmentNavigationDrawerInterceptor

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null
    var mInjectedLeftFragment: InjectedFragment? = null
    var mInjectedRightFragment: InjectedFragment? = null
    var mInjectedContentFragment: InjectedFragment? = null

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mFragmentNavigationDrawerInterceptor.onConfigurationChanged(newConfig)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        mFragmentNavigationDrawerInterceptor.onPrepareOptionsMenu(menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (!mFragmentNavigationDrawerInterceptor.onBackPressed())
            super.onBackPressed()
    }

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

    override fun onCreateFragment(): InjectedFragment = InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment") })

    override fun onFragmentLoaded(fragment: InjectedFragment) {
        mInjectedContentFragment = fragment
    }

    // ======== HasFragmentNavigationDrawerInterceptor =======================================================

    override fun onCreateContentDrawerFragment(gravity: Int): InjectedFragment = when (gravity) {
        Gravity.START -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment LEFT") })
        Gravity.END -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment RIGHT") })
        else -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment LEFT") })
    }

    override fun onContentDrawerFragmentLoaded(gravity: Int, fragment: InjectedFragment) {
        when (gravity) {
            Gravity.START -> mInjectedLeftFragment = fragment
            Gravity.END -> mInjectedRightFragment = fragment
        }
    }

    override fun onCreateContentDrawerView(gravity: Int): View = when (gravity) {
        Gravity.START -> left_offscreen_container
        Gravity.END -> right_offscreen_container
        else -> left_offscreen_container
    }

    override fun onCreateDrawerLayout(): DrawerLayout = drawer_layout

    override fun onDrawerLayoutCreated(drawerLayout: DrawerLayout) {}

    override fun onActionBarDrawerToggleCreated(drawerToggle: ActionBarDrawerToggle) {}

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        when (interceptor) {
            is AutoInflateLayoutInterceptor -> mAutoInflateLayoutInterceptor = interceptor
            is InjectFragmentInterceptor -> mInjectFragmentInterceptor = interceptor
            is FragmentNavigationDrawerInterceptor -> mFragmentNavigationDrawerInterceptor = interceptor
        }
    }
}