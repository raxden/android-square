package com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
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

/**
 * Created by agomez on 21/05/2015.
 *
<android.support.v4.widget.DrawerLayout
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:elevation="10dp">

<android.support.constraint.ConstraintLayout
android:layout_width="match_parent"
android:layout_height="match_parent">

<android.support.v7.widget.Toolbar
android:id="@+id/toolbar_view"
android:layout_width="match_parent"
android:layout_height="?attr/actionBarSize"
android:background="@color/colorPrimary"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent">

<android.support.v7.widget.AppCompatTextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="center"
android:text="Toolbar" />

</android.support.v7.widget.Toolbar>

<FrameLayout
android:id="@+id/container_view"
android:layout_width="0dp"
android:layout_height="0dp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toBottomOf="@+id/toolbar_view" />

</android.support.constraint.ConstraintLayout>

<FrameLayout
android:id="@+id/left_offscreen_container"
android:layout_width="330dp"
android:layout_height="match_parent"
android:layout_gravity="start" />

<FrameLayout
android:id="@+id/right_offscreen_container"
android:layout_width="330dp"
android:layout_height="match_parent"
android:layout_gravity="end" />

</android.support.v4.widget.DrawerLayout>
 *
 */
class FragmentNavigationDrawerActivityInterceptor<TFragment : Fragment>(
        callback: HasFragmentNavigationDrawerInterceptor<TFragment>)
    : ActivityInterceptor<FragmentNavigationDrawerInterceptor, HasFragmentNavigationDrawerInterceptor<TFragment>>(callback),
        FragmentNavigationDrawerInterceptor {

    private val mContentDrawerView: MutableMap<Int, View?> = mutableMapOf()
    private var mToolbar: Toolbar? = null
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mActivity: AppCompatActivity

    private var mDrawerListener: DrawerListener? = null
    private var mDrawerOpenListenerList: MutableMap<Int, MutableList<DrawerOpenListener>?> = mutableMapOf()
    private var mDrawerCloseListenerList: MutableMap<Int, MutableList<DrawerCloseListener>?> = mutableMapOf()

    private var mContainerFragmentMap: MutableMap<Int, TFragment?> = mutableMapOf()

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


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mActivity = activity as AppCompatActivity
        mContentDrawerView[Gravity.START] = mCallback.onCreateContentDrawerView(Gravity.START)
        mContentDrawerView[Gravity.END] = mCallback.onCreateContentDrawerView(Gravity.END)

        mDrawerLayout = mCallback.onCreateDrawerLayout().let {
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

        getFragmentManager(activity)?.also { fm ->
            if (savedInstanceState == null) {
                mContentDrawerView.keys.forEach { gravity ->
                    mContainerFragmentMap[gravity] = mContentDrawerView[gravity]?.let { view ->
                        mCallback.onCreateContentDrawerFragment(gravity).also {
                            fm.beginTransaction()
                                    .replace(view.id, it, it.javaClass.simpleName)
                                    .commit()
                            mCallback.onContentDrawerFragmentLoaded(gravity, it)
                        }
                    }
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityStarted(activity, savedInstanceState)

        mDrawerToggle.syncState()

        getFragmentManager(activity)?.also { fm ->
            mContentDrawerView.keys.forEach { gravity ->
                mContainerFragmentMap[gravity] = mContentDrawerView[gravity]?.let { view ->
                    (fm.findFragmentById(view.id) as? TFragment)?.also {
                        mCallback.onContentDrawerFragmentLoaded(gravity, it)
                    }
                }
            }
        }
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

    override fun onActivityDestroyed(activity: Activity) {
        super.onActivityDestroyed(activity)

        mContainerFragmentMap.clear()
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

    private fun initToolbar(savedInstanceState: Bundle?): Toolbar? = mCallback.onCreateToolbarView()?.also {
        mActivity.setSupportActionBar(it)
        mActivity.supportActionBar?.setDisplayShowTitleEnabled(false)
        it.setOnMenuItemClickListener { item -> mActivity.onOptionsItemSelected(item) }
        mCallback.onToolbarViewCreated(it)
    }

    private fun initActionBarDrawerToggle(drawerLayout: DrawerLayout): ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(mActivity, drawerLayout, R.string.square__drawer_open, R.string.square__drawer_close) {
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerOpened(drawerView)
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerSlide(drawerView, slideOffset)
                }

                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerStateChanged(newState)
                }
            }

    private fun initActionBarDrawerToggle(drawerLayout: DrawerLayout, toolbar: Toolbar): ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(mActivity, drawerLayout, toolbar, R.string.square__drawer_open, R.string.square__drawer_close) {
                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerClosed(drawerView)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerOpened(drawerView)
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerSlide(drawerView, slideOffset)
                }

                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                    this@FragmentNavigationDrawerActivityInterceptor.onDrawerStateChanged(newState)
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
