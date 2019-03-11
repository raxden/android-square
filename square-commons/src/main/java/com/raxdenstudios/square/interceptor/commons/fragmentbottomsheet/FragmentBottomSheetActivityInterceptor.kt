package com.raxdenstudios.square.interceptor.commons.fragmentbottomsheet

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

class FragmentBottomSheetActivityInterceptor<TView : View, TFragment : Fragment>(
        callback: HasFragmentBottomSheetInterceptor<TView, TFragment>
) : ActivityInterceptor<FragmentBottomSheetInterceptor, HasFragmentBottomSheetInterceptor<TView, TFragment>>(callback),
        FragmentBottomSheetInterceptor {

    companion object {
        private val IS_VISIBLE = FragmentBottomSheetActivityInterceptor::class.java.simpleName + "_isVisible"
        private val BOTTOM_SHEET_BEHAVIOUR_STATE = FragmentBottomSheetActivityInterceptor::class.java.simpleName + "_bottomSheetBehaviourState"
    }

    private lateinit var mBottomSheetView: TView
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<TView>
    private var mBottomSheetListenerList: MutableList<BottomSheetListener> = mutableListOf()

    private lateinit var mContainerView: View
    private var mCurrentPeekHeight: Int = 0
    private var mCurrentState: Int = 0
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

        savedInstanceState?.containsKey(IS_VISIBLE)?.also {
            if (!savedInstanceState.getBoolean(IS_VISIBLE)) {
                hide()
            }
        }

        getFragmentManager(activity)?.also { fm ->
            if (savedInstanceState != null) {
                mFragment = (fm.findFragmentById(mContainerView.id) as? TFragment)?.also {
                    mCallback.onBottomSheetFragmentLoaded(it)
                }
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        super.onActivityDestroyed(activity)
        mFragment = null
        mBottomSheetListenerList.clear()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        outState?.putInt(BOTTOM_SHEET_BEHAVIOUR_STATE, mBottomSheetBehavior.state)
        outState?.putBoolean(IS_VISIBLE, isVisible())

        super.onActivitySaveInstanceState(activity, outState)
    }

    override fun collapse() {
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun expand() {
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun hide() {
        if (!isVisible()) return
        mCurrentPeekHeight = mBottomSheetBehavior.peekHeight
        mCurrentState = mBottomSheetBehavior.state
        mBottomSheetBehavior.peekHeight = 0
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun show() {
        if (isVisible()) return
        mBottomSheetBehavior.peekHeight = mCurrentPeekHeight
        mBottomSheetBehavior.state = mCurrentState
    }

    override fun isVisible(): Boolean = mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED
            || mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED && (mBottomSheetBehavior.peekHeight == -1 || mBottomSheetBehavior.peekHeight > 0)

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
            behaviour.state = savedInstanceState?.getInt(BOTTOM_SHEET_BEHAVIOUR_STATE).takeIf { it != 0 }?.also { state ->
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