package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback
import kotlinx.android.synthetic.main.toolbar_activity.*

class ToolbarActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptorCallback,
        ToolbarInterceptorCallback {

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null

    // ======== InflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== ToolbarInterceptorCallback =========================================================

    override fun onCreateToolbarView(savedInstanceState: Bundle?): Toolbar = toolbar_view

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        mToolbarView = toolbar
    }

    // ======== SUPPORT METHODS ====================================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
        interceptorList.add(ToolbarActivityInterceptor(this, this))
    }

}