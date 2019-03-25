package com.raxdenstudios.square.interceptor.commons

import android.app.Activity
import android.support.v4.app.FragmentActivity
import com.raxdenstudios.square.InterceptorFactory
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentbottomnavigation.FragmentBottomNavigationActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentbottomnavigation.HasFragmentBottomNavigationInterceptor
import com.raxdenstudios.square.interceptor.commons.floatingactionbutton.FloatingActionButtonFragmentActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.floatingactionbutton.HasFloatingActionButtonFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet.FragmentBottomSheetActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet.HasFragmentBottomSheetInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.HasFragmentStatePagerInterceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.HasInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.InflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.InflateLayoutFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.HasInjectFragmentListInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer.HasFragmentNavigationDrawerInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentnavigationdrawer.FragmentNavigationDrawerActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.network.HasNetworkInterceptor
import com.raxdenstudios.square.interceptor.commons.network.NetworkActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.telephony.HasTelephonyInterceptor
import com.raxdenstudios.square.interceptor.commons.telephony.TelephonyActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarActivityInterceptor

class InterceptorCommonsFactory : InterceptorFactory() {

    override fun initActivityInterceptors(activity: Activity) : MutableList<Interceptor> {
        val list = mutableListOf<Interceptor>()
        (activity as? HasAutoInflateLayoutInterceptor)?.also { int -> list.add(AutoInflateLayoutActivityInterceptor(int)) }
        (activity as? HasInflateLayoutInterceptor)?.also { int -> list.add(InflateLayoutActivityInterceptor(int)) }
        (activity as? HasToolbarInterceptor)?.also { int -> list.add(ToolbarActivityInterceptor(int)) }
        (activity as? HasInjectFragmentInterceptor<*>)?.also { int -> list.add(InjectFragmentActivityInterceptor(int)) }
        (activity as? HasInjectFragmentListInterceptor<*>)?.also { int -> list.add(InjectFragmentListActivityInterceptor(int)) }
        (activity as? HasFragmentStatePagerInterceptor<*>)?.also { int -> list.add(FragmentStatePagerActivityInterceptor(int)) }
        (activity as? HasFloatingActionButtonFragmentInterceptor<*>)?.also { int -> list.add(FloatingActionButtonFragmentActivityInterceptor(int)) }
        (activity as? HasFragmentBottomNavigationInterceptor<*>)?.also { int -> list.add(FragmentBottomNavigationActivityInterceptor(int)) }
        (activity as? HasFragmentBottomSheetInterceptor<*, *>)?.also { int -> list.add(FragmentBottomSheetActivityInterceptor(int)) }
        (activity as? HasTelephonyInterceptor)?.also { int -> list.add(TelephonyActivityInterceptor(int)) }
        (activity as? HasFragmentNavigationDrawerInterceptor<*>)?.also { int -> list.add(FragmentNavigationDrawerActivityInterceptor(int)) }
        (activity as? HasNetworkInterceptor)?.also { int -> list.add(NetworkActivityInterceptor(int)) }
        return list
    }

    override fun initFragmentInterceptors(fragment: FragmentActivity) : MutableList<Interceptor> {
        val list = mutableListOf<Interceptor>()
        (fragment as? HasInflateLayoutInterceptor)?.also { int -> list.add(InflateLayoutFragmentInterceptor(int)) }
        return list
    }
}