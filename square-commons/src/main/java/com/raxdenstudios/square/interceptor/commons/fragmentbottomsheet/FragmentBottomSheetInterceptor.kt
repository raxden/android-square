package com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet

import com.raxdenstudios.square.interceptor.Interceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

interface FragmentBottomSheetInterceptor : Interceptor {
    fun collapse()
    fun show()
    fun hide()
    fun expand()
    fun isVisible(): Boolean
    fun isExpanded(): Boolean
    fun isCollapsed(): Boolean
    fun addBottomSheetListener(listener: FragmentBottomSheetActivityInterceptor.BottomSheetListener)
    fun removeBottomSheetListener(listener: FragmentBottomSheetActivityInterceptor.BottomSheetListener)
}
