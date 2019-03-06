package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

import android.support.v4.view.ViewPager
import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

interface FragmentStatePagerInterceptor : Interceptor {

    val isFirstPage: Boolean
    val isLastPage: Boolean
    val currentPage: Int
    fun setCurrentPage(page: Int)
    fun setCurrentPage(page: Int, smoothScroll: Boolean)
    fun nextPage(): Boolean
    fun previousPage(): Boolean
    fun addOnPageChangeListener(listener: ViewPager.OnPageChangeListener)
    fun removeOnPageChangeListener(listener: ViewPager.OnPageChangeListener)
}
