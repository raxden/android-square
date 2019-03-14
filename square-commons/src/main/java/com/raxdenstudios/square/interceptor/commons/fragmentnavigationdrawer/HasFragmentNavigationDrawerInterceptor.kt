package com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer

import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
interface HasFragmentNavigationDrawerInterceptor<TFragment : Fragment> : HasInterceptor {

    fun onCreateContentDrawerView(gravity: Int): View

    fun onCreateContentDrawerFragment(gravity: Int): TFragment

    fun onContentDrawerFragmentLoaded(gravity: Int, fragment: TFragment)

    fun onCreateDrawerLayout(): DrawerLayout

    fun onDrawerLayoutCreated(drawerLayout: DrawerLayout)

    fun onCreateToolbarView(): Toolbar?

    fun onToolbarViewCreated(toolbar: Toolbar)

    fun onActionBarDrawerToggleCreated(drawerToggle: ActionBarDrawerToggle)
}
