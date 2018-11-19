package com.raxdenstudios.square.interceptor.commons.navigationdrawer.base

import com.raxdenstudios.square.interceptor.Interceptor

interface BaseNavigationDrawerInterceptor: Interceptor {

    fun isOpenDrawer(gravity: Int): Boolean

    fun toggleDrawer(gravity: Int)

    fun openDrawer(gravity: Int, listener: BaseNavigationDrawerActivityInterceptor.DrawerOpenListener? = null)

    fun closeDrawer(gravity: Int, listener: BaseNavigationDrawerActivityInterceptor.DrawerCloseListener? = null)

}