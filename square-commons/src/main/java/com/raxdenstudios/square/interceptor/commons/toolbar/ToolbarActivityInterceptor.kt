package com.raxdenstudios.square.interceptor.commons.toolbar

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
class ToolbarActivityInterceptor(
        activity: AppCompatActivity,
        callback: ToolbarInterceptorCallback)
    : ActivityInterceptor<ToolbarInterceptorCallback>(activity, callback),
        ToolbarInterceptor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback?.onCreateToolbarView(savedInstanceState)?.let {toolbar ->
            (activity as AppCompatActivity).let {
                it.setSupportActionBar(toolbar)
                it.supportActionBar?.setDisplayShowTitleEnabled(false)
            }
            toolbar.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
            callback?.onToolbarViewCreated(toolbar)
        }
    }
}
