package com.raxdenstudios.square.interceptor.commons.navigationdrawer.fragment

import android.support.v4.app.Fragment
import com.raxdenstudios.square.interceptor.commons.navigationdrawer.base.HasNavigationDrawerBaseInterceptor

/**
 * Created by agomez on 21/05/2015.
 */
interface HasNavigationContentDrawerInterceptor<TFragment : Fragment> : HasNavigationDrawerBaseInterceptor {

    fun onCreateContentDrawerFragment(gravity: Int): TFragment

    fun onContentDrawerFragmentLoaded(gravity: Int, fragment: TFragment)
}
