package com.raxdenstudios.square.interceptor.commons.navigationdrawer.base

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.Menu
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.R

abstract class BaseNavigationDrawerActivityInterceptor<TCallback : BaseNavigationDrawerInterceptorCallback>(
        private val appCompatActivity: AppCompatActivity,
        callback: TCallback? = null)
    : ActivityInterceptor<TCallback>(appCompatActivity, callback),
        BaseNavigationDrawerInterceptor {

    protected var mContentDrawerView: MutableMap<Int, View?> = mutableMapOf()
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    private var mDrawerOpenListenerList: MutableMap<Int, MutableList<DrawerOpenListener>?> = mutableMapOf()
    private var mDrawerCloseListenerList: MutableMap<Int, MutableList<DrawerCloseListener>?> = mutableMapOf()

    interface DrawerCloseListener {
        fun onDrawerClosed()
    }

    interface DrawerOpenListener {
        fun onDrawerOpened()
    }

    private val drawerToggleDelegate: ActionBarDrawerToggle.Delegate?
        get() = appCompatActivity.drawerToggleDelegate

    override fun onConfigurationChanged(configuration: Configuration?) {
        mDrawerToggle?.onConfigurationChanged(configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContentDrawerView[Gravity.START] = callback?.onCreateContentDrawerView(savedInstanceState, Gravity.START)
        mContentDrawerView[Gravity.END] = callback?.onCreateContentDrawerView(savedInstanceState, Gravity.END)

        mDrawerLayout = callback?.onCreateDrawerLayout(savedInstanceState)?.let { drawerLayout ->
            // set a custom shadow that overlays the main content when the drawer opens
            drawerLayout.setDrawerShadow(R.drawable.square__drawer_shadow, GravityCompat.START)
            // ActionBarDrawerToggle ties together the the proper interactions between the sliding drawer and the action bar app icon
            mDrawerToggle = callback?.onCreateToolbarView(savedInstanceState)?.let { initActionBarDrawerToggle(it) } ?: initActionBarDrawerToggle()
            mDrawerToggle?.let { actionBarDrawerToggle ->
                actionBarDrawerToggle.toolbarNavigationClickListener = View.OnClickListener {
                    if (!actionBarDrawerToggle.isDrawerIndicatorEnabled) appCompatActivity.onBackPressed()
                }
                drawerLayout.addDrawerListener(actionBarDrawerToggle)
                callback?.onActionBarDrawerToggleCreated(actionBarDrawerToggle)
            }
            callback?.onDrawerLayoutCreated(drawerLayout)
            drawerLayout
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDrawerToggle?.syncState()
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        mDrawerToggle?.let {
            if (appCompatActivity.supportFragmentManager.backStackEntryCount > 0) {
                it.isDrawerIndicatorEnabled = false
                drawerToggleDelegate?.let { drawerToggleDelegate ->
                    it.setHomeAsUpIndicator(drawerToggleDelegate.themeUpIndicator)
                }
                mDrawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                it.isDrawerIndicatorEnabled = true
                mDrawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
    }

    override fun onBackPressed(): Boolean = when {
        isOpenDrawer(Gravity.START) -> {
            closeDrawer(Gravity.START)
            true
        }
        isOpenDrawer(Gravity.END) -> {
            closeDrawer(Gravity.END)
            true
        }
        else -> false
    }

    override fun onDestroy() {
        super.onDestroy()
        callback = null
    }

    override fun toggleDrawer(gravity: Int) {
        if (isOpenDrawer(gravity)) closeDrawer(gravity) else openDrawer(gravity)
    }

    override fun isOpenDrawer(gravity: Int): Boolean = mContentDrawerView[gravity]?.let { mDrawerLayout?.isDrawerOpen(it) }
            ?: false

    override fun openDrawer(gravity: Int, listener: DrawerOpenListener?) {
        listener?.let { mDrawerOpenListenerList[gravity]?.add(listener) }
        appCompatActivity.runOnUiThread {
            mContentDrawerView[gravity]?.let {
                mDrawerLayout?.openDrawer(it)
            }
        }
    }

    override fun closeDrawer(gravity: Int, listener: DrawerCloseListener?) {
        listener?.let { mDrawerCloseListenerList[gravity]?.add(listener) }
        appCompatActivity.runOnUiThread {
            mContentDrawerView[gravity]?.let {
                mDrawerLayout?.closeDrawer(it)
            }
        }
    }

    private fun initActionBarDrawerToggle(): ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(appCompatActivity, mDrawerLayout, R.string.square__drawer_open, R.string.square__drawer_close) {
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerOpened(drawerView)
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerSlide(drawerView, slideOffset)
                }

                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerStateChanged(newState)
                }
            }

    private fun initActionBarDrawerToggle(toolbar: Toolbar): ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(appCompatActivity, mDrawerLayout, toolbar, R.string.square__drawer_open, R.string.square__drawer_close) {
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerOpened(drawerView)
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerSlide(drawerView, slideOffset)
                }

                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                    this@BaseNavigationDrawerActivityInterceptor.onDrawerStateChanged(newState)
                }
            }

    private fun onDrawerClosed(drawerView: View) {
        onDrawerClosed(Gravity.START.takeIf { drawerView.id == mContentDrawerView[Gravity.START]?.id }
                ?: Gravity.END, drawerView)
    }

    private fun onDrawerClosed(gravity: Int, drawerView: View?) {
        mDrawerCloseListenerList[gravity]?.let {
            it.map { listener -> listener.onDrawerClosed() }
            it.clear()
        }
        drawerView?.let {
            invalidateOptionsMenu()
            callback?.onDrawerClosed(gravity, it)
        }
    }

    private fun onDrawerOpened(drawerView: View) {
        onDrawerOpened(Gravity.START.takeIf { drawerView.id == mContentDrawerView[Gravity.START]?.id }
                ?: Gravity.END, drawerView)
    }

    private fun onDrawerOpened(gravity: Int, drawerView: View?) {
        mDrawerOpenListenerList[gravity]?.let {
            it.map { listener -> listener.onDrawerOpened() }
            it.clear()
        }
        drawerView?.let {
            invalidateOptionsMenu()
            callback?.onDrawerOpened(gravity, it)
        }
    }

    private fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        onDrawerSlide(Gravity.START.takeIf { drawerView.id == mContentDrawerView[Gravity.START]?.id }
                ?: Gravity.END, drawerView, slideOffset)
    }

    private fun onDrawerSlide(gravity: Int, drawerView: View?, slideOffset: Float) {
        drawerView?.let {
            callback?.onDrawerSlide(gravity, it, slideOffset)
        }
    }

    private fun onDrawerStateChanged(newState: Int) {
        callback?.onDrawerStateChanged(newState)
    }

    private fun invalidateOptionsMenu() {
        appCompatActivity.invalidateOptionsMenu()
    }

}