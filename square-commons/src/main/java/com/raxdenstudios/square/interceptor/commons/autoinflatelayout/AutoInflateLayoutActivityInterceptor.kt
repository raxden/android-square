package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class AutoInflateLayoutActivityInterceptor(
        callback: HasAutoInflateLayoutInterceptor
) : ActivityInterceptor<HasAutoInflateLayoutInterceptor>(activity, callback) {

    private var mLayoutId: Int = 0
    private var mInflateLayout: View? = null

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
        onCreateView(activity.layoutInflater)?.let {
            mInflateLayout = it
            activity.setContentView(it)
            onContentViewCreated(it, savedInstanceState)
        }
    }

    private fun onCreateView(layoutInflater: LayoutInflater): View? = when {
        mLayoutId != 0 -> layoutInflater.inflate(mLayoutId, null)
        else -> {
            ResourceUtils.getLayoutId(activity, layoutName).let { layoutId ->
                if (layoutId != 0) layoutInflater.inflate(layoutId, null)
                else null
            }
        }
    }

    private fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        callback?.onContentViewCreated(view, savedInstanceState)
    }

    override fun setLayoutId(layoutId: Int) {
        mLayoutId = layoutId
    }

}

