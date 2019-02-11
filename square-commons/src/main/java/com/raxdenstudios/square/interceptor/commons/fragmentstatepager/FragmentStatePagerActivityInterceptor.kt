package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

import android.app.Activity
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
        callback: HasFragmentStatePagerInterceptor<TFragment>
) : ActivityInterceptor<FragmentStatePagerInterceptor<TFragment>, HasFragmentStatePagerInterceptor<TFragment>>(callback),
        FragmentStatePagerInterceptor<TFragment> {

    private lateinit var mViewPager: ViewPager
    private var mAdapter: FragmentStatePagerInterceptorAdapter? = null

    override val numPages: Int
        get() = mAdapter?.count ?: 0

    override val currentPosition: Int
        get() = mViewPager.currentItem

    override val currentFragment: TFragment?
        get() = mAdapter?.getFragment(currentPosition)

    override val isFirstPage: Boolean
        get() = currentPosition == 0

    override val isLastPage: Boolean
        get() = currentPosition == numPages - 1

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            mAdapter?.getFragment(position)?.let { mCallback.onFragmentSelected(it, position) }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mViewPager = mCallback.onCreateViewPager(savedInstanceState).let { viewPager ->
            (activity as? FragmentActivity)?.let {
                mAdapter = FragmentStatePagerInterceptorAdapter(activity.supportFragmentManager)
                viewPager.adapter = mAdapter
                viewPager.addOnPageChangeListener(onPageChangeListener)
            }
            mCallback.onViewPagerCreated(viewPager)
            viewPager
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        mViewPager.removeOnPageChangeListener(onPageChangeListener)
        super.onActivityDestroyed(activity)
    }

    override fun setCurrentPage(page: Int) {
        mViewPager.currentItem = page
    }

    override fun setCurrentPage(page: Int, smoothScroll: Boolean) {
        mViewPager.setCurrentItem(page, smoothScroll)
    }

    override fun nextPage(): TFragment? = if (isLastPage) null else {
        mViewPager.currentItem = currentPosition + 1
        currentFragment
    }

    override fun previousPage(): TFragment? = if (isFirstPage) null else {
        mViewPager.currentItem = currentPosition - 1
        currentFragment
    }

    private inner class FragmentStatePagerInterceptorAdapter internal constructor(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): TFragment = mCallback.onCreateFragment(position)

        override fun getCount(): Int = mCallback.viewPagerElements

        override fun instantiateItem(container: ViewGroup, position: Int): TFragment {
            val fragment = super.instantiateItem(container, position) as TFragment
            mCallback.onFragmentLoaded(fragment, position)
            return fragment
        }

        fun getFragment(position: Int): TFragment = instantiateItem(mViewPager, position)
    }
}
