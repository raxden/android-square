package com.raxdenstudios.square.interceptor.commons.bottomnavigation

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasBottomNavigationInterceptor<T : Fragment> : HasInterceptor {

    fun onCreateBottomNavigationView(): BottomNavigationView

    fun onBottomNavigationViewCreated(bottomNavigationView: BottomNavigationView)

    fun onLoadFragmentContainer(): View

    fun onCreateFragment(position: Int): T

    fun onFragmentLoaded(position: Int, fragment: T)

    fun onPageSelected(position: Int)
}
