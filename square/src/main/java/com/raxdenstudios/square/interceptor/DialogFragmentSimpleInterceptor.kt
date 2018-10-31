package com.raxdenstudios.square.interceptor

import android.support.v4.app.DialogFragment

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an DialogFragment interceptor.
 */
abstract class DialogFragmentSimpleInterceptor(
        dialogFragment: DialogFragment)
    : DialogFragmentInterceptor<InterceptorCallback>(dialogFragment)
