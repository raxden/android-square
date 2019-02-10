package com.raxdenstudios.square.interceptor.commons.autoinflatelayout

import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 */
interface HasAutoInflateLayoutInterceptor : HasInterceptor {

    fun onContentViewCreated(view: View, savedInstanceState: Bundle?)
}
