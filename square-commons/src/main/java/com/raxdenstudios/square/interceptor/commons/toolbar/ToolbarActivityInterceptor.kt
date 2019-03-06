package com.raxdenstudios.square.interceptor.commons.toolbar

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
class ToolbarActivityInterceptor(
        callback: HasToolbarInterceptor
) : ActivityInterceptor<ToolbarInterceptor, HasToolbarInterceptor>(callback),
        ToolbarInterceptor {

    private var mToolbar: Toolbar? = null

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mToolbar = (activity as? AppCompatActivity)?.let {
            initToolbar(it)
        }
    }

    private fun initToolbar(activity: AppCompatActivity): Toolbar? = mCallback.onCreateToolbarView().also {
        activity.setSupportActionBar(it)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        it.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
        mCallback.onToolbarViewCreated(it)
    }
}
