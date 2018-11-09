package com.raxdenstudios.square.interceptor.commons.handleextras

import android.os.Bundle
import android.support.v4.app.FragmentActivity

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class HandleExtrasActivityInterceptor(
        activity: FragmentActivity,
        callback: HandleExtrasInterceptorCallback)
    : ActivityInterceptor<HandleExtrasInterceptorCallback>(activity, callback),
        HandleExtrasInterceptor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callback?.onHandleExtras(savedInstanceState, activity.intent.extras ?: Bundle())
    }

}
