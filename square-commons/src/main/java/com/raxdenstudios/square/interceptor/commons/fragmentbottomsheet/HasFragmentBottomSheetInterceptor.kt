package com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet

import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasFragmentBottomSheetInterceptor<TView: View, T : Fragment> : HasInterceptor {

    fun onCreateBottomSheetView(): TView

    fun onBottomSheetBehaviourCreated(bottomSheetView: BottomSheetBehavior<TView>)

    fun onLoadBottomSheetFragmentContainer(): View

    fun onCreateBottomSheetFragment(): T

    fun onBottomSheetFragmentLoaded(fragment: T)
}
