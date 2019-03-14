package com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer

import android.content.res.Configuration
import android.view.Menu
import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface FragmentNavigationDrawerInterceptor : Interceptor {

    fun onBackPressed(): Boolean

    fun onConfigurationChanged(configuration: Configuration?)

    fun onPrepareOptionsMenu(menu: Menu?)

    fun isOpenDrawer(gravity: Int): Boolean

    fun toggleDrawer(gravity: Int)

    fun setDrawerListener(listener: FragmentNavigationDrawerActivityInterceptor.DrawerListener? = null)

    fun openDrawer(gravity: Int, listener: FragmentNavigationDrawerActivityInterceptor.DrawerOpenListener? = null)

    fun closeDrawer(gravity: Int, listener: FragmentNavigationDrawerActivityInterceptor.DrawerCloseListener? = null)
}
