package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragmentlist.InjectFragmentListInterceptorCallback
import kotlinx.android.synthetic.main.inject_fragment_list_activity.*

class InjectFragmentListActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptorCallback,
        InjectFragmentListInterceptorCallback<InjectFragmentListActivity.InjectedFragment> {

    var mContentView: View? = null

    var mFirstFragment: Fragment? = null
    var mSecondFragment: Fragment? = null
    var mThirdFragment: Fragment? = null

    // ======== AutoInflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== InjectFragmentListInterceptorCallback ===============================================

    override val fragmentCount: Int
        get() = 3

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?, position: Int): View? = when(position) {
        0 -> container_first_view
        1 -> container_second_view
        2 -> container_third_view
        else -> null
    }

    override fun onCreateFragment(position: Int): InjectedFragment? = when(position) {
        0 -> InjectedFragment.newInstance(intent.extras)
        1 -> InjectedFragment.newInstance(intent.extras)
        2 -> InjectedFragment.newInstance(intent.extras)
        else -> null
    }

    override fun onFragmentLoaded(fragment: InjectedFragment?, position: Int) {
        when(position) {
            0 -> mFirstFragment = fragment
            1 -> mSecondFragment = fragment
            2 -> mThirdFragment = fragment
        }
    }

    // ======== SUPPORT METHODS ====================================================================

    override fun setupInterceptors(interceptorList: MutableList<ActivityInterceptor<*>>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
        interceptorList.add(InjectFragmentListActivityInterceptor(this, this))
    }

    class InjectedFragment: Fragment() {

        companion object {
            fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }
    }

}