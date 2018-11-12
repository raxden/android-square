package com.raxdenstudios.square.interceptor.commons.navigationcontentdrawer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by agomez on 21/05/2015.
 */
interface NavigationContentDrawerInterceptorCallback<T : Fragment> : NavigationDrawerInterceptorCallback {

    fun onCreateContentDrawerFragment(): T

    fun onContentDrawerFragmentLoaded(fragment: T)

}
