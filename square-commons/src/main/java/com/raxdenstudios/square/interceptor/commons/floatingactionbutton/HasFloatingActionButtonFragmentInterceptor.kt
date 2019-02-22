package com.raxdenstudios.square.interceptor.commons.floatingactionbutton

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasFloatingActionButtonFragmentInterceptor<TMasterFragment : Fragment, TDetailFragment : Fragment> : HasInterceptor {

    fun onLoadFloatingActionButton(): FloatingActionButton

    fun onCreateToolbarView(savedInstanceState: Bundle?): Toolbar

    fun onToolbarViewCreated(toolbar: Toolbar)

    fun onLoadFragmentContainer(): View

    fun onCreateMasterFragment(): TMasterFragment

    fun onFragmentMasterLoaded(fragment: TMasterFragment)

    fun onCreateDetailFragment(): TDetailFragment

    fun onFragmentDetailLoaded(fragment: TDetailFragment)
}
