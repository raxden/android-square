package com.raxdenstudios.square.interceptor.commons.openhelper

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.support.v4.app.FragmentActivity

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by agomez on 08/05/2015.
 */
class OpenHelperActivityInterceptor<T : SQLiteOpenHelper>(
        activity: FragmentActivity,
        callback: OpenHelperInterceptorCallback<T>)
    : ActivityInterceptor<OpenHelperInterceptorCallback<T>>(activity, callback),
        OpenHelperInterceptor {

    private var mOpenHelper: SQLiteOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOpenHelper = callback?.onCreateOpenHelper(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mOpenHelper?.close()
        mOpenHelper = null
    }
}
