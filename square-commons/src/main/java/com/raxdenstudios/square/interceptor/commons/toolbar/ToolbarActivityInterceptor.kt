package com.raxdenstudios.square.interceptor.commons.toolbar

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by agomez on 21/05/2015.
 *
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
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
 */
class ToolbarActivityInterceptor(
        callback: HasToolbarInterceptor
) : ActivityInterceptor<ToolbarInterceptor, HasToolbarInterceptor>(callback),
        ToolbarInterceptor {

    private var mToolbar: Toolbar? = null

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mToolbar = (activity as? AppCompatActivity)?.let {
            initToolbar(it)
        }
    }

    private fun initToolbar(activity: AppCompatActivity): Toolbar? = mCallback.onCreateToolbarView().also {
        activity.setSupportActionBar(it)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        it.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
        mCallback.onToolbarViewCreated(it)
    }
}
