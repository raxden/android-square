package com.raxdenstudios.square.interceptor.commons

import android.app.Activity
import android.support.v4.app.FragmentActivity
import com.raxdenstudios.square.InterceptorFactory
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor

class InterceptorCommonsFactory : InterceptorFactory() {

    override fun initActivityInterceptors(activity: Activity, list: MutableList<Interceptor>) {
        when (activity) {
            is HasAutoInflateLayoutInterceptor -> list.add(AutoInflateLayoutActivityInterceptor(activity))
        }
    }

    override fun initFragmentInterceptors(fragment: FragmentActivity, list: MutableList<Interceptor>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}