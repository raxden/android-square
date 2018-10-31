package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View

import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.utils.ResourceUtils
import com.raxdenstudios.square.utils.StringUtils

import java.util.Locale

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
        mInflateLayout = onCreateView(activity.layoutInflater)
        if (mInflateLayout != null) {
            activity.setContentView(mInflateLayout)
            if (callback != null) {
                callback!!.onContentViewCreated(mInflateLayout!!, savedInstanceState!!)
            }
        }
    }

    private fun onCreateView(inflater: LayoutInflater): View? {
        return inflateLayout(inflater)
    }

    private fun inflateLayout(inflater: LayoutInflater): View? {
        if (mLayoutId != 0) {
            return inflater.inflate(mLayoutId, null)
        } else {
            val layoutId = ResourceUtils.getLayoutId(activity, layoutName)
            if (layoutId > 0) {
                return inflater.inflate(layoutId, null)
            }
        }
        return null
    }

    override fun setLayoutId(layoutId: Int) {
        mLayoutId = layoutId
    }

}

