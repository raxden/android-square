package com.raxdenstudios.square.sample.commons

import android.support.v7.app.AppCompatActivity
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor

class AutoInflateLayoutActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null

    var mContentView: View? = null

    // ======== HasInflateLayoutInterceptor ====================================================

    override fun onContentViewCreated(view: View) {
        mContentView = view
    }

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
    }
}