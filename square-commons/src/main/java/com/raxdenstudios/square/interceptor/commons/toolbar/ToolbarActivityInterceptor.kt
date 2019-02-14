package com.raxdenstudios.square.interceptor.commons.toolbar

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
class ToolbarActivityInterceptor(
        callback: HasToolbarInterceptor
) : ActivityInterceptor<ToolbarInterceptor, HasToolbarInterceptor>(callback),
        ToolbarInterceptor {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mCallback.onCreateToolbarView(savedInstanceState).let { toolbar ->
            (activity as AppCompatActivity).let {
                it.setSupportActionBar(toolbar)
                it.supportActionBar?.setDisplayShowTitleEnabled(false)
            }
            toolbar.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
            mCallback.onToolbarViewCreated(toolbar)
        }
    }
}
