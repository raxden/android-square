package com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer

import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by agomez on 21/05/2015.

    <android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/coordinator_layout_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

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

    </android.support.design.widget.CoordinatorLayout>
 */
interface HasFragmentNavigationDrawerInterceptor<TFragment : Fragment> : HasInterceptor {

    fun onCreateContentDrawerView(gravity: Int): View

    fun onCreateContentDrawerFragment(gravity: Int): TFragment

    fun onContentDrawerFragmentLoaded(gravity: Int, fragment: TFragment)

    fun onCreateDrawerLayout(): DrawerLayout

    fun onDrawerLayoutCreated(drawerLayout: DrawerLayout)

    fun onCreateToolbarView(): Toolbar?

    fun onToolbarViewCreated(toolbar: Toolbar)

    fun onActionBarDrawerToggleCreated(drawerToggle: ActionBarDrawerToggle)
}
