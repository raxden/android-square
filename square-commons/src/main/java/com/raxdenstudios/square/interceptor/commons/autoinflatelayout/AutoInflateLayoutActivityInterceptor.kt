package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.utils.ResourceUtils
import com.raxdenstudios.square.utils.StringUtils
import java.util.*

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class AutoInflateLayoutActivityInterceptor(
        activity: FragmentActivity,
        callback: AutoInflateLayoutInterceptorCallback)
    : ActivityInterceptor<AutoInflateLayoutInterceptorCallback>(activity, callback),
        AutoInflateLayoutInterceptor {

    private var mLayoutId: Int = 0
    private var mInflateLayout: View? = null

    private val layoutName: String
        get() {
            val className = activity.javaClass.simpleName.toLowerCase()
            return StringUtils.join(className.split("(?=\\p{Upper})".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray(), "_").toLowerCase(Locale.getDefault())
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
                layoutInflater.inflate(layoutId, null).takeIf { layoutId > 0 }
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

