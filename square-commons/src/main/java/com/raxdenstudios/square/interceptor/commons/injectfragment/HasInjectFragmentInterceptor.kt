package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasInjectFragmentInterceptor<T : Fragment> : HasInterceptor<InjectFragmentInterceptor> {

    fun onLoadFragmentContainer(savedInstanceState: Bundle?): View

    fun onCreateFragment(): T

    fun onFragmentLoaded(fragment: T)
}
