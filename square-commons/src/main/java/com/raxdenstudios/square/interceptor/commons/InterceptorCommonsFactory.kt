package com.raxdenstudios.square.interceptor.commons

import android.app.Activity
import android.support.v4.app.FragmentActivity
import com.raxdenstudios.square.InterceptorFactory
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.HasFragmentStatePagerInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.HasInjectFragmentListInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListActivityInterceptor

class InterceptorCommonsFactory : InterceptorFactory() {

    override fun initActivityInterceptors(activity: Activity, list: MutableList<Interceptor>) {
        when (activity) {
            is HasAutoInflateLayoutInterceptor -> list.add(AutoInflateLayoutActivityInterceptor(activity))
            is HasInjectFragmentInterceptor<*> -> list.add(InjectFragmentActivityInterceptor(activity))
            is HasInjectFragmentListInterceptor<*> -> list.add(InjectFragmentListActivityInterceptor(activity))
            is HasFragmentStatePagerInterceptor<*> -> list.add(FragmentStatePagerActivityInterceptor(activity))
        }
    }

    override fun initFragmentInterceptors(fragment: FragmentActivity, list: MutableList<Interceptor>) {

    }

}