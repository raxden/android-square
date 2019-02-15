package com.raxdenstudios.square.interceptor.commons.navigationdrawer

import com.raxdenstudios.square.interceptor.commons.navigationdrawer.base.NavigationDrawerActivityBaseInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
class NavigationDrawerActivityInterceptor(
        callback: HasNavigationDrawerInterceptor
) : NavigationDrawerActivityBaseInterceptor<NavigationDrawerInterceptor, HasNavigationDrawerInterceptor>(callback),
        NavigationDrawerInterceptor
