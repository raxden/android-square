package com.raxdenstudios.square.interceptor.commons.inflatelayout

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InflateLayoutActivityInterceptor(
        callback: HasInflateLayoutInterceptor
) : ActivityInterceptor<InflateLayoutInterceptor, HasInflateLayoutInterceptor>(callback),
        InflateLayoutInterceptor {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)
        activity?.let {
            getLayoutId(activity, getLayoutName(activity)).takeIf { it != 0 }?.let { mCallback.onLayoutLoaded(it) }
        }
    }

    private fun getLayoutName(activity: Activity): String {
        return activity.javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
    }

    private fun getLayoutId(context: Context, name: String?): Int = name?.takeIf { it.isNotEmpty() }?.let {
        context.resources.getIdentifier(it.replace("R.layout.", ""), "layout", context.packageName)
    } ?: 0
}

