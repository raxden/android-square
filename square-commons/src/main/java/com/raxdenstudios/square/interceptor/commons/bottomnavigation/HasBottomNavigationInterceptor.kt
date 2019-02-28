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

    fun onCreateFragment(itemId: Int): T

    fun onFragmentLoaded(itemId: Int, fragment: T)

    fun onMenuItemSelected(itemId: Int)
}
