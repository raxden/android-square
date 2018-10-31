package com.raxdenstudios.square.interceptor

import android.support.v4.app.FragmentActivity

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an activity interceptor.
 */
abstract class ActivitySimpleInterceptor(
        activity: FragmentActivity)
    : ActivityInterceptor<InterceptorCallback>(activity)