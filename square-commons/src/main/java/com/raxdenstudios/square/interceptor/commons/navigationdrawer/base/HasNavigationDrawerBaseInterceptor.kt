package com.raxdenstudios.square.interceptor.commons.navigationdrawer.base

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

interface HasNavigationDrawerBaseInterceptor : HasInterceptor {

    fun onCreateContentDrawerView(savedInstanceState: Bundle?, gravity: Int): View


    fun onCreateDrawerLayout(savedInstanceState: Bundle?): DrawerLayout

    fun onDrawerLayoutCreated(drawerLayout: DrawerLayout)


    fun onCreateToolbarView(savedInstanceState: Bundle?): Toolbar?

    fun onToolbarViewCreated(toolbar: Toolbar)


    fun onActionBarDrawerToggleCreated(drawerToggle: ActionBarDrawerToggle)


    fun onDrawerClosed(gravity: Int, drawerView: View)

    fun onDrawerOpened(gravity: Int, drawerView: View)

    fun onDrawerSlide(gravity: Int, drawerView: View, slideOffset: Float)

    fun onDrawerStateChanged(newState: Int)
}