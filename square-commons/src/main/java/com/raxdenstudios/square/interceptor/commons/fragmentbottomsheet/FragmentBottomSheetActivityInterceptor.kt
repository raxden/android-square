package com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

class FragmentBottomSheetActivityInterceptor<TView : View, TFragment : Fragment>(
        callback: HasFragmentBottomSheetInterceptor<TView, TFragment>
) : ActivityInterceptor<FragmentBottomSheetInterceptor, HasFragmentBottomSheetInterceptor<TView, TFragment>>(callback),
        FragmentBottomSheetInterceptor {

    private lateinit var mBottomSheetView: TView
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<TView>
    private var mBottomSheetListenerList: MutableList<BottomSheetListener> = mutableListOf()

    private lateinit var mContainerView: View
    private var mFragment: TFragment? = null

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mBottomSheetView = mCallback.onCreateBottomSheetView()
        mBottomSheetBehavior = initBottomSheetBehaviour(savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            mContainerView = mCallback.onLoadBottomSheetFragmentContainer()
            if (savedInstanceState == null) {
                mFragment = mCallback.onCreateBottomSheetFragment().also {
                    fm.beginTransaction()
                            .replace(mContainerView.id, it, it.javaClass.simpleName)
                            .commit()
                    mCallback.onBottomSheetFragmentLoaded(it)
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityStarted(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            if (savedInstanceState != null) {
                mFragment = (fm.findFragmentById(mContainerView.id) as? TFragment)?.also {
                    mCallback.onBottomSheetFragmentLoaded(it)
                }
            }
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        outState?.putInt("bottomSheetBehaviourState", mBottomSheetBehavior.state)

        super.onActivitySaveInstanceState(activity, outState)
    }

    override fun collapse() {
//        val navBarHeight = if (Utils.hasNavigationBar(getContext())) 0 else Utils.getNavigationBarHeight(getContext())
//        val peekHeight = getContext().getResources().getDimensionPixelSize(R.dimen.peek_height) - navBarHeight
//        mBottomSheetBehavior.peekHeight = peekHeight
//        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun expandPartial() {
//        mBottomSheetBehavior.peekHeight = getContext().getResources().getDimensionPixelSize(R.dimen.partial_collapse_height)
//        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun expand() {
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun hide() {
        mBottomSheetBehavior.peekHeight = 0
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun isPartialExpanded(): Boolean {
//        return mBottomSheetBehavior.peekHeight == getContext().getResources().getDimensionPixelSize(R.dimen.partial_collapse_height) && mBottomSheetBehaviourState == BottomSheetBehavior.STATE_COLLAPSED
        return false
    }

    override fun isHidden(): Boolean = mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED && mBottomSheetBehavior.peekHeight == 0

    override fun isExpanded(): Boolean = mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED

    override fun isCollapsed(): Boolean = mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED

    interface BottomSheetListener {
        fun onStateChanged(bottomSheet: View, newState: Int)
        fun onSlide(bottomSheet: View, slideOffset: Float)
    }

    override fun addBottomSheetListener(listener: BottomSheetListener) {
        if (!mBottomSheetListenerList.contains(listener))
            mBottomSheetListenerList.add(listener)
    }

    override fun removeBottomSheetListener(listener: BottomSheetListener) {
        if (mBottomSheetListenerList.contains(listener))
            mBottomSheetListenerList.remove(listener)
    }

    private fun initBottomSheetBehaviour(savedInstanceState: Bundle?): BottomSheetBehavior<TView> {
        return BottomSheetBehavior.from<TView>(mBottomSheetView).also { behaviour ->
            behaviour.state = savedInstanceState?.getInt("bottomSheetBehaviourState").takeIf { it != 0 }?.also { state ->
                state
            } ?: BottomSheetBehavior.STATE_COLLAPSED
            behaviour.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    mBottomSheetListenerList.forEach {
                        it.onStateChanged(bottomSheet, newState)
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    mBottomSheetListenerList.forEach {
                        it.onSlide(bottomSheet, slideOffset)
                    }
                }
            })
            mCallback.onBottomSheetBehaviourCreated(behaviour)
        }
    }
}