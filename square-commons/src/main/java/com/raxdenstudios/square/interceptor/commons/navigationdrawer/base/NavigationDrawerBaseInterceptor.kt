package com.raxdenstudios.square.interceptor.commons.navigationdrawer.base

import android.content.res.Configuration
import android.view.Menu
import com.raxdenstudios.square.interceptor.Interceptor

interface NavigationDrawerBaseInterceptor: Interceptor {

    fun onBackPressed(): Boolean

    fun onConfigurationChanged(configuration: Configuration?)

    fun onPrepareOptionsMenu(menu: Menu?)

    fun isOpenDrawer(gravity: Int): Boolean

    fun toggleDrawer(gravity: Int)

    fun openDrawer(gravity: Int, listener: NavigationDrawerActivityBaseInterceptor.DrawerOpenListener? = null)

    fun closeDrawer(gravity: Int, listener: NavigationDrawerActivityBaseInterceptor.DrawerCloseListener? = null)
}