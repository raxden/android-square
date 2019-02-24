package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasInjectFragmentInterceptor<TFragment : Fragment> : HasInterceptor {

    fun onLoadFragmentContainer(): View

    fun onCreateFragment(): TFragment

    fun onFragmentLoaded(fragment: TFragment)
}
