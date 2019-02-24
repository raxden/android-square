package com.raxdenstudios.square.interceptor.commons.navigationdrawer.base

import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

interface HasNavigationDrawerBaseInterceptor : HasInterceptor {

    fun onCreateContentDrawerView(gravity: Int): View


    fun onCreateDrawerLayout(): DrawerLayout

    fun onDrawerLayoutCreated(drawerLayout: DrawerLayout)


    fun onCreateToolbarView(): Toolbar?

    fun onToolbarViewCreated(toolbar: Toolbar)


    fun onActionBarDrawerToggleCreated(drawerToggle: ActionBarDrawerToggle)
}