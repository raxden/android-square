package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.ViewGroup

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
class FragmentStatePagerActivityInterceptor<TFragment : Fragment>(
        activity: FragmentActivity,
        callback: FragmentStatePagerInterceptorCallback<TFragment>)
    : ActivityInterceptor<FragmentStatePagerInterceptorCallback<TFragment>>(activity, callback),
        FragmentStatePagerInterceptor<TFragment> {

    private var mViewPager: ViewPager? = null
    private var mAdapter: FragmentStatePagerInterceptorAdapter? = null

    override val numPages: Int
        get() = mAdapter?.count ?: 0

    override val currentPosition: Int
        get() = mViewPager?.currentItem ?: 0

    override val currentFragment: TFragment?
        get() = mAdapter?.getFragment(currentPosition)

    override val isFirstPage: Boolean
        get() = currentPosition == 0

    override val isLastPage: Boolean
        get() = currentPosition == numPages - 1

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            val fragment = mAdapter?.getFragment(position)
            callback.onFragmentSelected(fragment, position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewPager = callback?.onCreateViewPager(savedInstanceState)?.let {
            mAdapter = FragmentStatePagerInterceptorAdapter(activity.supportFragmentManager)
            it.adapter = mAdapter
            it.addOnPageChangeListener(onPageChangeListener)
            callback?.onViewPagerCreated(it)
            it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewPager?.removeOnPageChangeListener(onPageChangeListener)
    }

    override fun setCurrentPage(page: Int) {
        mViewPager?.currentItem = page
    }

    override fun setCurrentPage(page: Int, smoothScroll: Boolean) {
        mViewPager?.setCurrentItem(page, smoothScroll)
    }

    override fun nextPage(): TFragment? = if (isLastPage) null else {
        mViewPager?.currentItem = currentPosition + 1
        currentFragment
    }

    override fun previousPage(): TFragment? = if (isFirstPage) null else {
        mViewPager?.currentItem = currentPosition - 1
        currentFragment
    }

    private inner class FragmentStatePagerInterceptorAdapter internal constructor(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): TFragment? = callback?.onCreateFragment(position)

        override fun getCount(): Int = callback?.viewPagerElements ?: 0

        override fun instantiateItem(container: ViewGroup, position: Int): TFragment {
            val fragment = super.instantiateItem(container, position) as TFragment
            callback?.onFragmentLoaded(fragment, position)
            return fragment
        }

        fun getFragment(position: Int): TFragment? = mViewPager?.let { instantiateItem(it, position) }

    }

}
