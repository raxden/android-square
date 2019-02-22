package com.raxdenstudios.square.interceptor.commons.injectfragmentlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasInjectFragmentListInterceptor<T : Fragment> : HasInterceptor {

    val fragmentCount: Int

    fun onLoadFragmentContainer(position: Int): View

    fun onCreateFragment(position: Int): T

    fun onFragmentLoaded(fragment: T, position: Int)
}
