package com.raxdenstudios.square.utils

import android.content.Context

/**
 * Created by Ángel Gómez on 09/06/2017.
 */

object ResourceUtils {

    fun getLayoutId(context: Context, name: String?): Int = name?.takeIf { it.isNotEmpty() }?.let {
        context.resources.getIdentifier(it.replace("R.layout.", ""), "layout", context.packageName)
    } ?: 0

}
