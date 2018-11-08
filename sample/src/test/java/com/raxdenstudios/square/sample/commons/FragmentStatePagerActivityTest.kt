package com.raxdenstudios.square.sample.commons

import com.raxdenstudios.square.sample.BaseTest
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric

class FragmentStatePagerActivityTest : BaseTest() {

    lateinit var activity: FragmentStatePagerActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentStatePagerActivity::class.java).apply {
            create().start().resume()
        }.get()
    }

    @Test
    fun checkIfLayoutIsLoaded() {
        assertNotNull(activity.mContentView)
    }

    @Test
    fun checkIfFragmentPagerIsLoaded() {
        assertNotNull(activity.mFragmentStatePagerInterceptor)
    }

    @Test
    fun checkPager() {
        assertEquals(activity.mFragmentStatePagerInterceptor?.currentPosition, 0)
        assertNotNull(activity.mFragmentStatePagerInterceptor?.currentFragment)

        assertNotNull(activity.mFragmentStatePagerInterceptor?.nextPage())
        assertEquals(activity.mFragmentStatePagerInterceptor?.currentPosition, 1)
        assertNotNull(activity.mFragmentStatePagerInterceptor?.currentFragment)

        assertNotNull(activity.mFragmentStatePagerInterceptor?.nextPage())
        assertEquals(activity.mFragmentStatePagerInterceptor?.currentPosition, 2)
        assertNotNull(activity.mFragmentStatePagerInterceptor?.currentFragment)

        assertTrue(activity.mFragmentStatePagerInterceptor?.nextPage() == null)
        assertEquals(activity.mFragmentStatePagerInterceptor?.currentPosition, 2)
        assertNotNull(activity.mFragmentStatePagerInterceptor?.currentFragment)

        assertNotNull(activity.mFragmentStatePagerInterceptor?.previousPage())
        assertEquals(activity.mFragmentStatePagerInterceptor?.currentPosition, 1)
        assertNotNull(activity.mFragmentStatePagerInterceptor?.currentFragment)

        assertNotNull(activity.mFragmentStatePagerInterceptor?.previousPage())
        assertEquals(activity.mFragmentStatePagerInterceptor?.currentPosition, 0)
        assertNotNull(activity.mFragmentStatePagerInterceptor?.currentFragment)

        assertTrue(activity.mFragmentStatePagerInterceptor?.previousPage() == null)
        assertEquals(activity.mFragmentStatePagerInterceptor?.currentPosition, 0)
        assertNotNull(activity.mFragmentStatePagerInterceptor?.currentFragment)

        assertNotNull(activity.mFirstFragment)
        assertNotNull(activity.mSecondFragment)
        assertNotNull(activity.mThirdFragment)
    }

}
