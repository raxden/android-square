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
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.HasNavigationDrawerInterceptor
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.NavigationDrawerActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.NavigationDrawerInterceptor
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.fragment.HasNavigationContentDrawerInterceptor
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.fragment.NavigationContentDrawerActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.telephony.HasTelephonyInterceptor
import com.raxdenstudios.square.interceptor.commons.telephony.TelephonyActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarActivityInterceptor

class InterceptorCommonsFactory : InterceptorFactory() {

    override fun initActivityInterceptors(activity: Activity, list: MutableList<Interceptor>) {
        (activity as? HasAutoInflateLayoutInterceptor)?.let { int -> list.add(AutoInflateLayoutActivityInterceptor(int)) }
        (activity as? HasInjectFragmentInterceptor<*>)?.let { int -> list.add(InjectFragmentActivityInterceptor(int)) }
        (activity as? HasInjectFragmentListInterceptor<*>)?.let { int -> list.add(InjectFragmentListActivityInterceptor(int)) }
        (activity as? HasFragmentStatePagerInterceptor<*>)?.let { int -> list.add(FragmentStatePagerActivityInterceptor(int)) }
        (activity as? HasToolbarInterceptor)?.let { int -> list.add(ToolbarActivityInterceptor(int)) }
        (activity as? HasTelephonyInterceptor)?.let { int -> list.add(TelephonyActivityInterceptor(int)) }
        when (activity) {
            is HasNavigationContentDrawerInterceptor<*> -> list.add(NavigationContentDrawerActivityInterceptor(activity))
            is HasNavigationDrawerInterceptor -> list.add(NavigationDrawerActivityInterceptor(activity))
        }
    }

    override fun initFragmentInterceptors(fragment: FragmentActivity, list: MutableList<Interceptor>) {

    }

}