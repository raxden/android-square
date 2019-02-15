package com.raxdenstudios.square.interceptor.commons.navigationdrawer.base

import android.app.Activity
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

abstract class NavigationDrawerActivityBaseInterceptor<TInterceptor : NavigationDrawerBaseInterceptor, TCallback : HasNavigationDrawerBaseInterceptor>(
        callback: TCallback
) : ActivityInterceptor<TInterceptor, TCallback>(callback),
        NavigationDrawerBaseInterceptor {

    protected val mContentDrawerView: MutableMap<Int, View?> = mutableMapOf()
    protected var mToolbar: Toolbar? = null
    protected lateinit var mDrawerLayout: DrawerLayout
    protected lateinit var mDrawerToggle: ActionBarDrawerToggle
    protected lateinit var mActivity: AppCompatActivity

    protected var mDrawerListener: DrawerListener? = null
    protected var mDrawerOpenListenerList: MutableMap<Int, MutableList<DrawerOpenListener>?> = mutableMapOf()
    protected var mDrawerCloseListenerList: MutableMap<Int, MutableList<DrawerCloseListener>?> = mutableMapOf()

    interface DrawerListener {
        fun onDrawerSlide(gravity: Int, drawerView: View, slideOffset: Float)

        fun onDrawerOpened(gravity: Int, drawerView: View)

        fun onDrawerClosed(gravity: Int, drawerView: View)

        fun onDrawerStateChanged(newState: Int)
    }

    interface DrawerCloseListener {
        fun onDrawerClosed()
    }

    interface DrawerOpenListener {
        fun onDrawerOpened()
    }

    private val drawerToggleDelegate: ActionBarDrawerToggle.Delegate?
        get() = mActivity.drawerToggleDelegate

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mActivity = activity as AppCompatActivity
        mContentDrawerView[Gravity.START] = mCallback.onCreateContentDrawerView(savedInstanceState, Gravity.START)
        mContentDrawerView[Gravity.END] = mCallback.onCreateContentDrawerView(savedInstanceState, Gravity.END)

        mDrawerLayout = mCallback.onCreateDrawerLayout(savedInstanceState).let {
            setDrawerShadow(it)
            mToolbar = initToolbar(savedInstanceState)
            mDrawerToggle = initDrawerToggle(it, mToolbar)
            mDrawerToggle.apply {
                toolbarNavigationClickListener = View.OnClickListener {
                    if (!isDrawerIndicatorEnabled) mActivity.onBackPressed()
                }
                it.addDrawerListener(this)
                mCallback.onActionBarDrawerToggleCreated(this)
            }
            mCallback.onDrawerLayoutCreated(it)
            it
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        mDrawerToggle.syncState()
        super.onActivityStarted(activity)
    }

    override fun onConfigurationChanged(configuration: Configuration?) {
        mDrawerToggle.onConfigurationChanged(configuration)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        mDrawerToggle.apply {
            if (mActivity.supportFragmentManager.backStackEntryCount > 0) {
                isDrawerIndicatorEnabled = false
                drawerToggleDelegate?.let { drawerToggleDelegate ->
                    setHomeAsUpIndicator(drawerToggleDelegate.themeUpIndicator)
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                isDrawerIndicatorEnabled = true
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
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

    override fun toggleDrawer(gravity: Int) {
        if (isOpenDrawer(gravity)) closeDrawer(gravity) else openDrawer(gravity)
    }

    override fun isOpenDrawer(gravity: Int): Boolean = mContentDrawerView[gravity]?.let { mDrawerLayout.isDrawerOpen(it) }
            ?: false

    override fun openDrawer(gravity: Int, listener: DrawerOpenListener?) {
        listener?.let { mDrawerOpenListenerList[gravity]?.add(listener) }
        mActivity.runOnUiThread {
            mContentDrawerView[gravity]?.let {
                mDrawerLayout.openDrawer(it)
            }
        }
    }

    override fun closeDrawer(gravity: Int, listener: DrawerCloseListener?) {
        listener?.let { mDrawerCloseListenerList[gravity]?.add(listener) }
        mActivity.runOnUiThread {
            mContentDrawerView[gravity]?.let {
                mDrawerLayout.closeDrawer(it)
            }
        }
    }

    override fun setDrawerListener(listener: DrawerListener?) {
        mDrawerListener = listener
    }

    private fun setDrawerShadow(drawerLayout: DrawerLayout) {
        drawerLayout.setDrawerShadow(R.drawable.square__drawer_shadow, GravityCompat.START)
    }

    private fun initDrawerToggle(drawerLayout: DrawerLayout, toolbar: Toolbar?): ActionBarDrawerToggle = toolbar?.let {
        initActionBarDrawerToggle(drawerLayout, it)
    } ?: initActionBarDrawerToggle(drawerLayout)

    private fun initToolbar(savedInstanceState: Bundle?): Toolbar? = mCallback.onCreateToolbarView(savedInstanceState)?.let {
        mActivity.setSupportActionBar(it)
        mActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        it.setOnMenuItemClickListener { item -> mActivity.onOptionsItemSelected(item) }
        mCallback.onToolbarViewCreated(it)
        it
    }

    private fun initActionBarDrawerToggle(drawerLayout: DrawerLayout): ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(mActivity, drawerLayout, R.string.square__drawer_open, R.string.square__drawer_close) {
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerOpened(drawerView)
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerSlide(drawerView, slideOffset)
                }

                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerStateChanged(newState)
                }
            }

    private fun initActionBarDrawerToggle(drawerLayout: DrawerLayout, toolbar: Toolbar): ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(mActivity, drawerLayout, toolbar, R.string.square__drawer_open, R.string.square__drawer_close) {
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerOpened(drawerView)
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerSlide(drawerView, slideOffset)
                }

                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                    this@NavigationDrawerActivityBaseInterceptor.onDrawerStateChanged(newState)
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
        drawerView?.apply {
            mActivity.invalidateOptionsMenu()
            mDrawerListener?.onDrawerClosed(gravity, this)
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
        drawerView?.apply {
            mActivity.invalidateOptionsMenu()
            mDrawerListener?.onDrawerOpened(gravity, this)
        }
    }

    private fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        onDrawerSlide(Gravity.START.takeIf { drawerView.id == mContentDrawerView[Gravity.START]?.id }
                ?: Gravity.END, drawerView, slideOffset)
    }

    private fun onDrawerSlide(gravity: Int, drawerView: View?, slideOffset: Float) {
        drawerView?.let { mDrawerListener?.onDrawerSlide(gravity, it, slideOffset) }
    }

    private fun onDrawerStateChanged(newState: Int) {
        mDrawerListener?.onDrawerStateChanged(newState)
    }

}