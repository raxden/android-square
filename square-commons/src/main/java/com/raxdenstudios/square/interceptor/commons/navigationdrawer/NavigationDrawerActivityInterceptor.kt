package com.raxdenstudios.square.interceptor.commons.navigationdrawer

import android.support.v7.app.AppCompatActivity
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.base.BaseNavigationDrawerActivityInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
class NavigationDrawerActivityInterceptor(
        appCompatActivity: AppCompatActivity,
        callback: NavigationDrawerInterceptorCallback)
    : BaseNavigationDrawerActivityInterceptor<NavigationDrawerInterceptorCallback>(appCompatActivity, callback),
        NavigationDrawerInterceptor
