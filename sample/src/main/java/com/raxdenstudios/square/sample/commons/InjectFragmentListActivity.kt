package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.HasInjectFragmentListInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListInterceptor
import kotlinx.android.synthetic.main.inject_fragment_list_activity.*

class InjectFragmentListActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasInjectFragmentListInterceptor<InjectFragmentListActivity.InjectedFragment> {

    lateinit var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor
    lateinit var mInjectFragmentListInterceptor: InjectFragmentListInterceptor

    var mContentView: View? = null

    var mFirstFragment: Fragment? = null
    var mSecondFragment: Fragment? = null
    var mThirdFragment: Fragment? = null

    // ======== InflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== InjectFragmentListInterceptorCallback ===============================================

    override val fragmentCount: Int
        get() = 3

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?, position: Int): View = when (position) {
        0 -> container_first_view
        1 -> container_second_view
        2 -> container_third_view
        else -> container_first_view
    }

    override fun onCreateFragment(position: Int): InjectedFragment = when (position) {
        0 -> InjectedFragment.newInstance(intent.extras)
        1 -> InjectedFragment.newInstance(intent.extras)
        2 -> InjectedFragment.newInstance(intent.extras)
        else -> InjectedFragment.newInstance(intent.extras)
    }

    override fun onFragmentLoaded(fragment: InjectedFragment, position: Int) {
        when (position) {
            0 -> mFirstFragment = fragment
            1 -> mSecondFragment = fragment
            2 -> mThirdFragment = fragment
        }
    }

    // ==========================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as AutoInflateLayoutInterceptor
        mInjectFragmentListInterceptor = interceptor as InjectFragmentListInterceptor
    }

    class InjectedFragment : Fragment() {

        companion object {
            fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }
    }

}