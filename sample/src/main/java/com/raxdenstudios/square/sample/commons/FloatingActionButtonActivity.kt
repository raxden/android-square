package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.floatingactionbutton.FloatingActionButtonFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.floatingactionbutton.FragmentType
import com.raxdenstudios.square.interceptor.commons.floatingactionbutton.HasFloatingActionButtonFragmentInterceptor
import kotlinx.android.synthetic.main.floating_action_button_activity.*

class FloatingActionButtonActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasFloatingActionButtonFragmentInterceptor<Fragment> {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null
    private var mFloatingActionButtonFragmentInterceptor: FloatingActionButtonFragmentInterceptor? = null

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null
    var mMasterFragment: InjectedFragment? = null
    var mDetailFragment: InjectedFragment? = null

    override fun onBackPressed() {
        if (mFloatingActionButtonFragmentInterceptor?.onBackPressed(this) == false)
            super.onBackPressed()
    }

    // ======== HasInflateLayoutInterceptor ========================================================

    override fun onContentViewCreated(view: View) {
        mContentView = view
    }

    // ======== HasFloatingActionButtonFragmentInterceptor =========================================

    override fun onLoadFloatingActionButton(): FloatingActionButton = floating_action_button

    override fun onCreateToolbarView(): Toolbar = toolbar_view

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        mToolbarView = toolbar
    }

    override fun onLoadFragmentContainer(): View = container_view

    override fun onCreateFragment(type: FragmentType): Fragment = when (type) {
        FragmentType.MASTER -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Master Fragment") })
        FragmentType.DETAIL -> InjectedFragment.newInstance(Bundle().apply { putString("title", "Detail Fragment") })
    }

    override fun onFragmentLoaded(type: FragmentType, fragment: Fragment) {
        when (type) {
            FragmentType.MASTER -> mMasterFragment = fragment as InjectedFragment
            FragmentType.DETAIL -> mDetailFragment = fragment as InjectedFragment
        }
    }

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
        mFloatingActionButtonFragmentInterceptor = interceptor as? FloatingActionButtonFragmentInterceptor
    }
}