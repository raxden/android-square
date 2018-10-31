package com.raxdenstudios.square.manager

import android.app.Application
import android.support.multidex.MultiDexApplication
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.raxdenstudios.square.utils.LibraryHelper

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

object InterceptorManagerFactory {

    fun <T> buildManager(type: T): InterceptorManager<*, *>? {
        return when (type) {
            LibraryHelper.isMultiDexAvailable() && type is MultiDexApplication -> ApplicationMultiDexInterceptorManager(type as MultiDexApplication)
            is Application -> ApplicationMultiDexInterceptorManager(type as MultiDexApplication)
            is FragmentActivity -> ActivityInterceptorManager(type as FragmentActivity)
            is DialogFragment -> DialogFragmentInterceptorManager(type as DialogFragment)
            is Fragment -> FragmentInterceptorManager(type as Fragment)
            else -> null
        }
    }
}
