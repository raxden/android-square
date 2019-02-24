package com.raxdenstudios.square.interceptor.commons.floatingactionbutton

import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasFloatingActionButtonFragmentInterceptor<TFragment : Fragment> : HasInterceptor {

    fun onLoadFloatingActionButton(): FloatingActionButton

    fun onCreateToolbarView(): Toolbar

    fun onToolbarViewCreated(toolbar: Toolbar)

    fun onLoadFragmentContainer(): View

    fun onCreateFragment(type: FragmentType): TFragment

    fun onFragmentLoaded(type: FragmentType, fragment: TFragment)
}
