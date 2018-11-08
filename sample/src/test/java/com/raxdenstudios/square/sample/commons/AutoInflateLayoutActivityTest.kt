package com.raxdenstudios.square.sample

import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric

class AutoInflateLayoutActivityTest : BaseTest() {

    lateinit var activity: AutoInflateLayoutActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(AutoInflateLayoutActivity::class.java).apply {
            create().start().resume()
        }.get()
    }

    @Test
    fun checkIfLayoutIsLoaded() {
        assertNotNull(activity.mContentView)
    }

}
