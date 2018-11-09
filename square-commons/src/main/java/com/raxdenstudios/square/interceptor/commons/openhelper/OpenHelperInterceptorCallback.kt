package com.raxdenstudios.square.interceptor.commons.openhelper

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle

import com.raxdenstudios.square.interceptor.InterceptorCallback

/**
 * Created by agomez on 08/05/2015.
 */
interface OpenHelperInterceptorCallback<T : SQLiteOpenHelper> : InterceptorCallback {

    fun onCreateOpenHelper(savedInstanceState: Bundle?): T?

}
