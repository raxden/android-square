package com.raxdenstudios.square.interceptor.commons.navigationdrawer.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.Gravity
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.base.NavigationDrawerActivityBaseInterceptor

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
class NavigationContentDrawerActivityInterceptor<TFragment : Fragment>(
        callback: HasNavigationContentDrawerInterceptor<TFragment>)
    : NavigationDrawerActivityBaseInterceptor<NavigationContentDrawerInterceptor, HasNavigationContentDrawerInterceptor<TFragment>>(callback),
        NavigationContentDrawerInterceptor {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? FragmentActivity)?.apply {
            initFragment(this, Gravity.START, savedInstanceState)?.let { mCallback.onContentDrawerFragmentLoaded(Gravity.START, it) }
            initFragment(this, Gravity.END, savedInstanceState)?.let { mCallback.onContentDrawerFragmentLoaded(Gravity.END, it) }
        }
    }

    private fun initFragment(activity: FragmentActivity, gravity: Int, savedInstanceState: Bundle?): TFragment? {
        return mContentDrawerView[gravity]?.let { view ->
            savedInstanceState?.let {
                activity.supportFragmentManager.findFragmentById(view.id) as TFragment
            } ?: mCallback.onCreateContentDrawerFragment(gravity).let {
                activity.supportFragmentManager.beginTransaction().replace(view.id, it, it.javaClass.simpleName).commit()
                it
            }
        }
    }
}
