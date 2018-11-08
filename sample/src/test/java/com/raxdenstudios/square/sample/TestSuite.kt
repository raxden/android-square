package com.raxdenstudios.square.sample


import com.raxdenstudios.square.sample.commons.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        AutoInflateLayoutActivityTest::class,
        FragmentStatePagerActivityTest::class,
        InjectFragmentActivityTest::class,
        InjectFragmentListActivityTest::class,
        ToolbarActivityTest::class
)
class TestSuite
