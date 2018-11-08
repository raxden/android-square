package com.raxdenstudios.square.sample.commons

import com.raxdenstudios.square.sample.BaseTest
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric

class InjectFragmentActivityTest : BaseTest() {

    lateinit var activity: InjectFragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(InjectFragmentActivity::class.java).apply {
            create().start().resume()
        }.get()
    }

    @Test
    fun checkIfToolbarIsLoaded() {
        assertNotNull(activity.mInjectedFragment)
    }

}
