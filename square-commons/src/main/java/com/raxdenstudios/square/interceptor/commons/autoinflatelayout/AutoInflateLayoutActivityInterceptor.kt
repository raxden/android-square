package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.utils.ResourceUtils

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class AutoInflateLayoutActivityInterceptor(
        callback: HasAutoInflateLayoutInterceptor
) : ActivityInterceptor<AutoInflateLayoutInterceptor, HasAutoInflateLayoutInterceptor>(callback),
        AutoInflateLayoutInterceptor {

    private var mLayoutId: Int = 0

    override fun setLayoutId(layoutId: Int) {
        mLayoutId = layoutId
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)
        activity?.let { onCreateView(it) }?.let { view ->
            activity.setContentView(view)
            mCallback.onContentViewCreated(view, savedInstanceState)
        }
    }

    private fun onCreateView(activity: Activity): View? = when {
        mLayoutId != 0 -> activity.layoutInflater.inflate(mLayoutId, null)
        else -> {
            ResourceUtils.getLayoutId(activity, getLayoutName(activity)).let { layoutId ->
                if (layoutId != 0) activity.layoutInflater.inflate(layoutId, null)
                else null
            }
        }
    }

    private fun getLayoutName(activity: Activity): String {
        return activity.javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
    }
}

