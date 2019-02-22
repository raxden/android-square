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
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.fragment.HasNavigationContentDrawerInterceptor
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.fragment.NavigationContentDrawerInterceptor
import kotlinx.android.synthetic.main.navigation_drawer_activity.*

class NavigationDrawerActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasInjectFragmentInterceptor<InjectedFragment>,
        HasNavigationContentDrawerInterceptor<InjectedFragment> {

    private lateinit var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor
    private lateinit var mInjectFragmentInterceptor: InjectFragmentInterceptor
    private lateinit var mNavigationContentDrawerInterceptor: NavigationContentDrawerInterceptor

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null
    var mInjectedLeftFragment: InjectedFragment? = null
    var mInjectedRightFragment: InjectedFragment? = null
    var mInjectedContentFragment: InjectedFragment? = null

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mNavigationContentDrawerInterceptor.onConfigurationChanged(newConfig)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        mNavigationContentDrawerInterceptor.onPrepareOptionsMenu(menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (!mNavigationContentDrawerInterceptor.onBackPressed())
            super.onBackPressed()
    }

    // ======== HasInflateLayoutInterceptor ====================================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== HasToolbarInterceptor ==============================================================

    override fun onCreateToolbarView(savedInstanceState: Bundle?): Toolbar = toolbar_view

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        mToolbarView = toolbar
    }

    // ======== HasFloatingActionButtonFragmentInterceptor =======================================================

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = container_view

    override fun onCreateFragment(): InjectedFragment = InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment") })

    override fun onFragmentLoaded(fragment: InjectedFragment) {
        mInjectedContentFragment = fragment
    }

    // ======== HasNavigationContentDrawerInterceptor =======================================================

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

    override fun onCreateContentDrawerView(savedInstanceState: Bundle?, gravity: Int): View = when (gravity) {
        Gravity.START -> left_offscreen_container
        Gravity.END -> right_offscreen_container
        else -> left_offscreen_container
    }

    override fun onCreateDrawerLayout(savedInstanceState: Bundle?): DrawerLayout = drawer_layout

    override fun onDrawerLayoutCreated(drawerLayout: DrawerLayout) {}

    override fun onActionBarDrawerToggleCreated(drawerToggle: ActionBarDrawerToggle) {}

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        when (interceptor) {
            is AutoInflateLayoutInterceptor -> mAutoInflateLayoutInterceptor = interceptor
            is InjectFragmentInterceptor -> mInjectFragmentInterceptor = interceptor
            is NavigationContentDrawerInterceptor -> mNavigationContentDrawerInterceptor = interceptor
        }
    }
}