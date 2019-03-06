package com.raxdenstudios.square.interceptor.commons.fragmentstatepager

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.ViewGroup

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
class FragmentStatePagerActivityInterceptor<TFragment : Fragment>(
        callback: HasFragmentStatePagerInterceptor<TFragment>
) : ActivityInterceptor<FragmentStatePagerInterceptor, HasFragmentStatePagerInterceptor<TFragment>>(callback),
        FragmentStatePagerInterceptor {

    private lateinit var mViewPager: ViewPager
    private var mAdapter: FragmentStatePagerInterceptorAdapter? = null

    private val numPages: Int
        get() = mAdapter?.count ?: 0

    override val currentPage: Int
        get() = mViewPager.currentItem

    override val isFirstPage: Boolean
        get() = currentPage == 0

    override val isLastPage: Boolean
        get() = currentPage == numPages - 1

    private var mOnPageChangeListenerList: MutableList<ViewPager.OnPageChangeListener> = mutableListOf()

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (positionOffset != 0.0f || positionOffsetPixels != 0) return
            mOnPageChangeListenerList.forEach {
                it.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        }

        override fun onPageSelected(position: Int) {
            mOnPageChangeListenerList.forEach {
                it.onPageSelected(position)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            mOnPageChangeListenerList.forEach {
                it.onPageScrollStateChanged(state)
            }
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mViewPager = mCallback.onCreateViewPager().let { viewPager ->
            (activity as? FragmentActivity)?.let {
                mAdapter = FragmentStatePagerInterceptorAdapter(activity.supportFragmentManager)
                viewPager.adapter = mAdapter
                viewPager.addOnPageChangeListener(onPageChangeListener)
            }
            mCallback.onViewPagerCreated(viewPager)
            viewPager
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        mViewPager.removeOnPageChangeListener(onPageChangeListener)
        super.onActivityDestroyed(activity)
    }

    override fun setCurrentPage(page: Int) {
        mViewPager.currentItem = page
    }

    override fun setCurrentPage(page: Int, smoothScroll: Boolean) {
        mViewPager.setCurrentItem(page, smoothScroll)
    }

    override fun nextPage(): Boolean = if (isLastPage) false else {
        mViewPager.currentItem = currentPage + 1
        true
    }

    override fun previousPage(): Boolean = if (isFirstPage) false else {
        mViewPager.currentItem = currentPage - 1
        true
    }

    override fun addOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
        if (!mOnPageChangeListenerList.contains(listener))
            mOnPageChangeListenerList.add(listener)
    }

    override fun removeOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
        if (mOnPageChangeListenerList.contains(listener))
            mOnPageChangeListenerList.remove(listener)
    }

    private inner class FragmentStatePagerInterceptorAdapter internal constructor(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): TFragment {
          return mCallback.onCreateFragment(position)
        }

        override fun getCount(): Int = mCallback.viewPagerElements

        override fun instantiateItem(container: ViewGroup, position: Int): TFragment {
            val fragment = super.instantiateItem(container, position) as TFragment
            mCallback.onFragmentLoaded(position, fragment)
            return fragment
        }
    }
}
