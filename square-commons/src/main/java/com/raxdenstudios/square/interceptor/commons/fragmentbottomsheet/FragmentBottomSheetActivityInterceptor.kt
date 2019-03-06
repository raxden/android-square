package com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

class FragmentBottomSheetActivityInterceptor<TView : View, TFragment : Fragment>(
        callback: HasFragmentBottomSheetInterceptor<TView, TFragment>
) : ActivityInterceptor<FragmentBottomSheetInterceptor, HasFragmentBottomSheetInterceptor<TView, TFragment>>(callback),
        FragmentBottomSheetInterceptor {

    private lateinit var mBottomSheetView: TView
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<TView>
    private var mBottomSheetListenerList: MutableList<BottomSheetListener> = mutableListOf()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            mBottomSheetView = mCallback.onCreateBottomSheetView()
            mBottomSheetBehavior = initBottomSheetBehaviour(savedInstanceState)
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

    override fun hide() {
//        mBottomSheetBehavior.peekHeight = 0
//        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun expand() {
//        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun isHidden(): Boolean {
//        return mBottomSheetBehaviourState == BottomSheetBehavior.STATE_COLLAPSED && mBottomSheetBehavior.peekHeight == 0
        return false
    }

    override fun isPartialExpanded(): Boolean {
//        return mBottomSheetBehavior.peekHeight == getContext().getResources().getDimensionPixelSize(R.dimen.partial_collapse_height) && mBottomSheetBehaviourState == BottomSheetBehavior.STATE_COLLAPSED
        return false
    }

    override fun isExpanded(): Boolean {
//        return mBottomSheetBehaviourState == BottomSheetBehavior.STATE_EXPANDED
        return false
    }

    override fun isCollapsed(): Boolean {
//        return mBottomSheetBehaviourState == BottomSheetBehavior.STATE_COLLAPSED
        return false
    }

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
            behaviour.isHideable = false
            behaviour.peekHeight = 0
            behaviour.state = savedInstanceState?.getInt("bottomSheetBehaviourState")?.also { state ->
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
        }
    }

}