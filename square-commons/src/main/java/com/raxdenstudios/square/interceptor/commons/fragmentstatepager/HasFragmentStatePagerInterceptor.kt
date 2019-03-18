package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 20/12/2016.
 *
    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingBottom="@dimen/activity_vertical_margin">

        <android.support.v4.view.ViewPager
            android:id="@+id/view_pager"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </android.support.constraint.ConstraintLayout>
 */
interface HasFragmentStatePagerInterceptor<TFragment : Fragment> : HasInterceptor {

    val viewPagerElements: Int

    fun onCreateViewPager(): ViewPager

    fun onViewPagerCreated(viewPager: ViewPager)

    fun onCreateFragment(position: Int): TFragment

    fun onFragmentLoaded(position: Int, fragment: TFragment)
}
