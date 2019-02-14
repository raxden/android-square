package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

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
}
