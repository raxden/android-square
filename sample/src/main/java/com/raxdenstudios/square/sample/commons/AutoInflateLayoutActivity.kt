package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor

class AutoInflateLayoutActivity : AppCompatActivity(), HasAutoInflateLayoutInterceptor {

    lateinit var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor
    var mContentView: View? = null

    // ======== InflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    override fun onInterceptorAttached(interceptor: AutoInflateLayoutInterceptor) {
        mAutoInflateLayoutInterceptor = interceptor
    }

}