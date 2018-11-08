package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

import android.support.v4.app.Fragment

import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

interface FragmentStatePagerInterceptor<TFragment : Fragment> : Interceptor {

    val isFirstPage: Boolean

    val isLastPage: Boolean

    val numPages: Int

    val currentPosition: Int

    val currentFragment: TFragment?

    fun setCurrentPage(page: Int)

    fun setCurrentPage(page: Int, smoothScroll: Boolean)

    fun nextPage(): TFragment?

    fun previousPage(): TFragment?

}
