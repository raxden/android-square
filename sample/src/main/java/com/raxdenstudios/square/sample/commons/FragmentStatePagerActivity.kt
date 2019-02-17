package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.FragmentStatePagerInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentstatepager.HasFragmentStatePagerInterceptor
import kotlinx.android.synthetic.main.fragment_state_pager_activity.*

class FragmentStatePagerActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasFragmentStatePagerInterceptor<InjectedFragment> {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null
    private var mFragmentStatePagerInterceptor: FragmentStatePagerInterceptor? = null

    var mContentView: View? = null
    var mViewPagerView: View? = null

    var mFirstFragment: InjectedFragment? = null
    var mSecondFragment: InjectedFragment? = null
    var mThirdFragment: InjectedFragment? = null

    var mFragmentSelected: Int = 0
    var mFragmentScrolled: Int = 0

    // ======== HasInflateLayoutInterceptor ====================================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== HasFragmentStatePagerInterceptor ===================================================

    override val viewPagerElements: Int
        get() = 3

    override fun onCreateViewPager(savedInstanceState: Bundle?): ViewPager = view_pager

    override fun onViewPagerCreated(viewPager: ViewPager) {
        mViewPagerView = viewPager
    }

    override fun onCreateFragment(position: Int): InjectedFragment = when (position) {
        0 -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 1") })
        1 -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 2") })
        2 -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 3") })
        else -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 1") })
    }

    override fun onFragmentLoaded(fragment: InjectedFragment, position: Int) {
        when (position) {
            0 -> mFirstFragment = fragment
            1 -> mSecondFragment = fragment
            2 -> mThirdFragment = fragment
        }
    }

    override fun onPageSelected(position: Int) {
        mFragmentSelected = position
    }

    override fun onPageScrolled(position: Int) {
        mFragmentSelected = position
    }

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
        mFragmentStatePagerInterceptor = interceptor as? FragmentStatePagerInterceptor
    }
}