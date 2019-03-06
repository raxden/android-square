package com.raxdenstudios.square.sample.commons

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet.FragmentBottomSheetInterceptor
import com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet.HasFragmentBottomSheetInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor
import kotlinx.android.synthetic.main.fragment_bottom_sheet_activity.*

class FragmentBottomSheetActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasToolbarInterceptor,
        HasInjectFragmentInterceptor<InjectedFragment>,
        HasFragmentBottomSheetInterceptor<View, InjectedFragment> {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null
    private var mToolbarInterceptor: ToolbarInterceptor? = null
    private var mInjectFragmentInterceptor: InjectFragmentInterceptor? = null
    private var mBottomSheetInterceptor: FragmentBottomSheetInterceptor? = null

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null
    var mBottomSheetBehavior: BottomSheetBehavior<View>? = null
    var mInjectedFragment: InjectedFragment? = null
    var mInjectedBottomFragment: InjectedFragment? = null

    // ======== HasInflateLayoutInterceptor ====================================================

    override fun onContentViewCreated(view: View) {
        mContentView = view
    }

    // ======== HasToolbarInterceptor ==============================================================

    override fun onCreateToolbarView(): Toolbar = toolbar_view

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        mToolbarView = toolbar
    }

    // ======== HasInjectFragmentInterceptor =======================================================

    override fun onLoadFragmentContainer(): View = container_view

    override fun onCreateFragment(): InjectedFragment = InjectedFragment.newInstance(Bundle().apply { putString("title", "Fragment 1") })

    override fun onFragmentLoaded(fragment: InjectedFragment) {
        mInjectedFragment = fragment
    }

    // ======== HasFragmentBottomSheetInterceptor ==================================================

    override fun onCreateBottomSheetView(): View = bottom_sheet_view

    override fun onBottomSheetBehaviourCreated(bottomSheetView: BottomSheetBehavior<View>) {
        mBottomSheetBehavior = bottomSheetView
    }

    override fun onLoadBottomSheetFragmentContainer(): View = bottom_sheet_view

    override fun onCreateBottomSheetFragment(): InjectedFragment = InjectedFragment.newInstance(Bundle().apply {
        putString("title", "Bottom Sheet Fragment")
        putInt("backgroundColor", Color.parseColor("#ff0000"))
    })

    override fun onBottomSheetFragmentLoaded(fragment: InjectedFragment) {
        mInjectedBottomFragment = fragment
    }

    // =============================================================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
        mToolbarInterceptor = interceptor as? ToolbarInterceptor
        mInjectFragmentInterceptor = interceptor as? InjectFragmentInterceptor
        mBottomSheetInterceptor = interceptor as? FragmentBottomSheetInterceptor
    }
}