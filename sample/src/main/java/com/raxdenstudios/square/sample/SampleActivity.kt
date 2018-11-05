package com.raxdenstudios.square.sample

import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback

class SampleActivity : SquareActivity(), AutoInflateLayoutInterceptorCallback {

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun setupInterceptors(interceptorList: MutableList<ActivityInterceptor<*>>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
    }

}