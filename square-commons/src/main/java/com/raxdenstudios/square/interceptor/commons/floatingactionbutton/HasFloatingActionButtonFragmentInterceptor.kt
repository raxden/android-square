package com.raxdenstudios.square.interceptor.commons.floatingactionbutton

import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 *
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/white">

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

        <android.support.design.widget.FloatingActionButton
            android:id="@+id/floating_action_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="end|bottom"
            app:backgroundTint="@color/colorPrimary"
            app:rippleColor="@android:color/white"
            app:fabSize="normal"
            app:srcCompat="@drawable/ic_add_white_24dp"
            app:layout_constraintBottom_toBottomOf="@+id/container_view"
            app:layout_constraintEnd_toEndOf="parent"
            app:useCompatPadding="true" />

    </android.support.constraint.ConstraintLayout>
 */
interface HasFloatingActionButtonFragmentInterceptor<TFragment : Fragment> : HasInterceptor {

    fun onLoadFloatingActionButton(): FloatingActionButton

    fun onCreateToolbarView(): Toolbar

    fun onToolbarViewCreated(toolbar: Toolbar)

    fun onLoadFragmentContainer(): View

    fun onCreateFragment(type: FragmentType): TFragment

    fun onFragmentLoaded(type: FragmentType, fragment: TFragment)
}
