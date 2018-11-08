package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.SquareFragment
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.FragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptorCallback
import kotlinx.android.synthetic.main.fragment_state_pager_activity.*

class FragmentStatePagerActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptorCallback,
        FragmentStatePagerInterceptorCallback<Fragment> {

    var mContentView: View? = null
    var mViewPagerView: View? = null

    var mFirstFragment: Fragment? = null
    var mSecondFragment: Fragment? = null
    var mThirdFragment: Fragment? = null

    var mFragmentSelected: Int = 0

    var mFragmentStatePagerInterceptor: FragmentStatePagerInterceptor<Fragment>? = null

    // ======== AutoInflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== AutoInflateLayoutInterceptorCallback ===============================================

    override fun onCreateViewPager(savedInstanceState: Bundle?): ViewPager = content_view

    override fun onViewPagerCreated(viewPager: ViewPager) {
        mViewPagerView = viewPager
    }

    override fun onCreateFragment(position: Int): Fragment? = when (position) {
        0 -> InjectedFragment.newInstance(intent.extras)
        1 -> InjectedFragment.newInstance(intent.extras)
        2 -> InjectedFragment.newInstance(intent.extras)
        else -> null
    }

    override fun onFragmentLoaded(fragment: Fragment?, position: Int) {
        when (position) {
            0 -> mFirstFragment = fragment
            1 -> mSecondFragment = fragment
            2 -> mThirdFragment = fragment
        }
    }

    override fun onFragmentSelected(fragment: Fragment?, position: Int) {
        mFragmentSelected = position
    }

    override val viewPagerElements: Int
        get() = 3

    // ======== SUPPORT METHODS ====================================================================

    override fun setupInterceptors(interceptorList: MutableList<ActivityInterceptor<*>>) {
        mFragmentStatePagerInterceptor = FragmentStatePagerActivityInterceptor(this, this)
        interceptorList.add(mFragmentStatePagerInterceptor as FragmentStatePagerActivityInterceptor)
    }

    class InjectedFragment
        : SquareFragment(),
            AutoInflateViewInterceptorCallback {

        companion object {
            fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }

        override fun setupInterceptors(interceptorList: MutableList<FragmentInterceptor<*>>) {
            interceptorList.add(AutoInflateViewFragmentInterceptor(this, this))
        }
    }

}