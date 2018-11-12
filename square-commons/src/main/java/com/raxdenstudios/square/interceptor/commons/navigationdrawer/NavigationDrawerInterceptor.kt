package com.raxdenstudios.square.interceptor.commons.navigationdrawer

import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

interface NavigationDrawerInterceptor : Interceptor {

    fun isOpenDrawer(gravity: Int): Boolean

    fun toggleDrawer(gravity: Int)

    fun openDrawer(gravity: Int, listener: NavigationDrawerActivityInterceptor.DrawerOpenListener? = null)

    fun closeDrawer(gravity: Int, listener: NavigationDrawerActivityInterceptor.DrawerCloseListener? = null)

}
