package com.raxdenstudios.square.interceptor.commons.fullscreen

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.WindowManager

import com.raxdenstudios.square.interceptor.ActivitySimpleInterceptor

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
class FullScreenActivityInterceptor(activity: FragmentActivity)
    : ActivitySimpleInterceptor(activity),
        FullScreenInterceptor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

}
