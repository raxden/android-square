package com.raxdenstudios.square.interceptor.commons.navigationdrawer.injectfragment

import android.support.v4.app.Fragment
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.base.BaseNavigationDrawerInterceptorCallback

/**
 * Created by agomez on 21/05/2015.
 */
interface NavigationContentDrawerInterceptorCallback<T : Fragment> : BaseNavigationDrawerInterceptorCallback {

    fun onCreateContentDrawerFragment(gravity: Int): T?

    fun onContentDrawerFragmentLoaded(gravity: Int, fragment: T)

}
