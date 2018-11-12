package com.raxdenstudios.square.interceptor.commons.handlearguments

import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor

/**
 * Created by agomez on 22/05/2015.
 */
class HandleArgumentsDialogFragmentInterceptor(
        dialogFragment: DialogFragment,
        callback: HandleArgumentsInterceptorCallback)
    : DialogFragmentInterceptor<HandleArgumentsInterceptorCallback>(dialogFragment, callback),
        HandleArgumentsInterceptor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callback?.onHandleArguments(savedInstanceState, dialogFragment.arguments ?: Bundle())
    }

}
