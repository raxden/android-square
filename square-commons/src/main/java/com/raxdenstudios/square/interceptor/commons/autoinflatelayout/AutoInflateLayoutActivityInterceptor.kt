package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

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

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        onCreateView(activity)?.let { view ->
            activity.setContentView(view)
            mCallback.onContentViewCreated(view)
        }
    }

    private fun onCreateView(activity: Activity): View? = when {
        mLayoutId != 0 -> activity.layoutInflater.inflate(mLayoutId, null)
        else -> {
            getLayoutId(activity, getLayoutName(activity)).let { layoutId ->
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

    private fun getLayoutId(context: Context, name: String?): Int = name?.takeIf { it.isNotEmpty() }?.let {
        context.resources.getIdentifier(it.replace("R.layout.", ""), "layout", context.packageName)
    } ?: 0

}

