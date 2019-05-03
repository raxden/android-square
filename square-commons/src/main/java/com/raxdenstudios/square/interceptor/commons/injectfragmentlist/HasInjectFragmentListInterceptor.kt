package com.raxdenstudios.square.interceptor.commons.injectfragmentlist

import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:orientation="vertical">

        <FrameLayout
            android:id="@+id/container_first_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_weight="1"/>

        <FrameLayout
            android:id="@+id/container_second_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_weight="1"/>

        <FrameLayout
            android:id="@+id/container_third_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_weight="1"/>

    </LinearLayout>
 */
interface HasInjectFragmentListInterceptor<T : Fragment> : HasInterceptor {

    val fragmentCount: Int

    fun onLoadFragmentContainer(position: Int): View

    fun onCreateFragment(position: Int): T

    fun onFragmentLoaded(position: Int, fragment: T)
}
