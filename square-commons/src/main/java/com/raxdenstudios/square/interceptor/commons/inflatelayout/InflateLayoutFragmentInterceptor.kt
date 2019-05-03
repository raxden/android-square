package com.raxdenstudios.square.interceptor.commons.inflatelayout

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.raxdenstudios.square.interceptor.FragmentInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InflateLayoutFragmentInterceptor(
        callback: HasInflateLayoutInterceptor
) : FragmentInterceptor<InflateLayoutInterceptor, HasInflateLayoutInterceptor>(callback),
        InflateLayoutInterceptor {

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentAttached(fm, f, context)

        getLayoutId(context, getLayoutName(f)).takeIf { it != 0 }?.let { mCallback.onLayoutLoaded(it) }
    }

    private fun getLayoutName(fragment: Fragment): String {
        return fragment.javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
    }

    private fun getLayoutId(context: Context, name: String?): Int = name?.takeIf { it.isNotEmpty() }?.let {
        context.resources.getIdentifier(it.replace("R.layout.", ""), "layout", context.packageName)
    } ?: 0
}

