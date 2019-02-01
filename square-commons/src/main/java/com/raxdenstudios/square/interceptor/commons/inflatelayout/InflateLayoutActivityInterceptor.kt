package com.raxdenstudios.square.interceptor.commons.inflatelayout

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.utils.ResourceUtils

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InflateLayoutActivityInterceptor(
        activity: FragmentActivity,
        callback: InflateLayoutInterceptorCallback)
    : ActivityInterceptor<InflateLayoutInterceptorCallback>(activity, callback),
        InflateLayoutInterceptor {

    private var mLayoutId: Int = 0
    private val layoutName: String
        get() {
            return activity.javaClass.simpleName
                    .decapitalize()
                    .split("(?=\\p{Upper})".toRegex())
                    .joinToString(separator = "_")
                    .toLowerCase()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when {
            mLayoutId != 0 -> callback?.onLayoutIdLoaded(mLayoutId, savedInstanceState)
            else -> {
                ResourceUtils.getLayoutId(activity, layoutName).let { layoutId ->
                    if (layoutId != 0) callback?.onLayoutIdLoaded(layoutId, savedInstanceState)
                }
            }
        }
    }
}

