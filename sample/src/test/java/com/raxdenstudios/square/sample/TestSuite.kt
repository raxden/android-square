package com.raxdenstudios.square.sample


import com.raxdenstudios.square.sample.commons.AutoInflateLayoutActivityTest
import com.raxdenstudios.square.sample.commons.FragmentStatePagerActivityTest
import com.raxdenstudios.square.sample.commons.ToolbarActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        AutoInflateLayoutActivityTest::class,
        FragmentStatePagerActivityTest::class,
        ToolbarActivityTest::class
)
class TestSuite
