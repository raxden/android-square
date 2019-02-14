package com.raxdenstudios.square.interceptor.commons.toolbar

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
interface HasToolbarInterceptor : HasInterceptor {

    fun onCreateToolbarView(savedInstanceState: Bundle?): Toolbar

    fun onToolbarViewCreated(toolbar: Toolbar)
}
