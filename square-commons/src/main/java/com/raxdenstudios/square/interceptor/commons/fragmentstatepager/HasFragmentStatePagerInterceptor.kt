package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
interface HasFragmentStatePagerInterceptor<TFragment : Fragment> : HasInterceptor {

    val viewPagerElements: Int

    fun onCreateViewPager(savedInstanceState: Bundle?): ViewPager

    fun onViewPagerCreated(viewPager: ViewPager)

    fun onCreateFragment(position: Int): TFragment

    fun onFragmentLoaded(fragment: TFragment, position: Int)

    fun onFragmentSelected(fragment: TFragment, position: Int)
}
