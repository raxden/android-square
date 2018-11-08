package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback

class AutoInflateLayoutActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptorCallback {

    var mContentView: View? = null

    // ======== AutoInflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== SUPPORT METHODS ====================================================================

    override fun setupInterceptors(interceptorList: MutableList<ActivityInterceptor<*>>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
    }

}