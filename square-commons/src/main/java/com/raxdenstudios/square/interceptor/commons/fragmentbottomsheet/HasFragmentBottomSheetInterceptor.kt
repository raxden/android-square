package com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet

import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 *
    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
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

        <android.support.design.widget.CoordinatorLayout
            android:id="@+id/bottom_sheet_container_view"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHeight_percent="0.8"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="1">

            <FrameLayout
                android:id="@+id/bottom_sheet_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:layout_behavior="@string/bottom_sheet_behavior"/>

        </android.support.design.widget.CoordinatorLayout>

    </android.support.constraint.ConstraintLayout>
 */
interface HasFragmentBottomSheetInterceptor<TView: View, T : Fragment> : HasInterceptor {

    fun onCreateBottomSheetView(): TView

    fun onBottomSheetBehaviourCreated(bottomSheetView: BottomSheetBehavior<TView>)

    fun onLoadBottomSheetFragmentContainer(): View

    fun onCreateBottomSheetFragment(): T

    fun onBottomSheetFragmentLoaded(fragment: T)
}
