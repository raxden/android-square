package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.

    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <FrameLayout
            android:id="@+id/container_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </android.support.constraint.ConstraintLayout>
 */
interface HasInjectFragmentInterceptor<TFragment : Fragment> : HasInterceptor {

    fun onLoadFragmentContainer(): View

    fun onCreateFragment(): TFragment

    fun onFragmentLoaded(fragment: TFragment)
}
