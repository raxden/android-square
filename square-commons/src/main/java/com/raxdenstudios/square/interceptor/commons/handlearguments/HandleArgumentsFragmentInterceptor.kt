package com.raxdenstudios.square.interceptor.commons.handlearguments

import android.os.Bundle
import android.support.v4.app.Fragment

import com.raxdenstudios.square.interceptor.FragmentInterceptor

/**
 * Created by agomez on 22/05/2015.
 */
class HandleArgumentsFragmentInterceptor(
        fragment: Fragment,
        callback: HandleArgumentsInterceptorCallback)
    : FragmentInterceptor<HandleArgumentsInterceptorCallback>(fragment, callback),
        HandleArgumentsInterceptor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callback?.onHandleArguments(savedInstanceState, fragment.arguments ?: Bundle())
    }

}
