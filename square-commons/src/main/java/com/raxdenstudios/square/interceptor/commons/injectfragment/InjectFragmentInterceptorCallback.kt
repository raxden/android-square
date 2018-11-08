package com.raxdenstudios.square.interceptor.commons.injectfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
interface InjectFragmentInterceptorCallback<T : Fragment> : InterceptorCallback {

    fun onLoadFragmentContainer(savedInstanceState: Bundle?): View

    fun onCreateFragment(): T?

    fun onFragmentLoaded(fragment: T?)

}
