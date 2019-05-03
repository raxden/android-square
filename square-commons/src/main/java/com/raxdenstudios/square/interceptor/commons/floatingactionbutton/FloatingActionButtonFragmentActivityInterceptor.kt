package com.raxdenstudios.square.interceptor.commons.floatingactionbutton

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.R
import android.animation.ValueAnimator
import android.view.ViewAnimationUtils
import android.annotation.TargetApi
import android.animation.ArgbEvaluator
import android.content.res.Resources
import android.os.Build
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class FloatingActionButtonFragmentActivityInterceptor<TFragment : Fragment>(
        callback: HasFloatingActionButtonFragmentInterceptor<TFragment>
) : ActivityInterceptor<FloatingActionButtonFragmentInterceptor, HasFloatingActionButtonFragmentInterceptor<TFragment>>(callback),
        FloatingActionButtonFragmentInterceptor {

    private var mNavigationIcon: Int = R.drawable.square__ic_close_white_24dp
    private var mOriginalNavigationIcon: Drawable? = null
    private var mStartColorAnimation: Int = 0
    private var mEndColorAnimation: Int = 0
    private var mDurationAnimation: Long = 0

    private lateinit var mFloatingActionButton: FloatingActionButton
    private lateinit var mToolbar: Toolbar
    private lateinit var mContainerView: View
    private var mCurrentFragmentType: FragmentType = FragmentType.MASTER
    private var mContainerFragmentMap: MutableMap<FragmentType, TFragment?> = mutableMapOf()

    interface AnimationFinishedListener {
        fun onAnimationFinished()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            mCurrentFragmentType = restoreCurrentFragmentType(savedInstanceState)
            mFloatingActionButton = initFloatingActionButton(fm)
            mToolbar = initToolbar(activity as AppCompatActivity, fm)
            mContainerView = mCallback.onLoadFragmentContainer()
            if (savedInstanceState == null) {
                mContainerFragmentMap[mCurrentFragmentType] = mCallback.onCreateFragment(mCurrentFragmentType).also {
                    fm.beginTransaction()
                            .replace(mContainerView.id, it, it.javaClass.simpleName)
                            .commit()
                    mCallback.onFragmentLoaded(mCurrentFragmentType, it)
                }
            }
            fm.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {

                override fun onFragmentViewCreated(fm: FragmentManager, fragment: Fragment, view: View, savedInstanceState: Bundle?) {
                    if (mContainerFragmentMap[FragmentType.DETAIL] == fragment)
                        registerCircularRevealAnimation(view, object : AnimationFinishedListener {
                            override fun onAnimationFinished() {
                            }
                        })
                }
            }, true)
        }
    }

    override fun onActivityStarted(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityStarted(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            if (fm.backStackEntryCount > 0) {
                mToolbar.navigationIcon = activity?.let { ContextCompat.getDrawable(it, mNavigationIcon) }
                mFloatingActionButton.hide()
            } else {
                mToolbar.navigationIcon = mOriginalNavigationIcon
                mFloatingActionButton.show()
            }
            if (savedInstanceState != null) {
                mContainerFragmentMap[mCurrentFragmentType] = (fm.findFragmentById(mContainerView.id) as? TFragment)?.also {
                    mCallback.onFragmentLoaded(mCurrentFragmentType, it)
                }
            }
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        outState?.putInt("currentFragmentType", mCurrentFragmentType.ordinal)

        super.onActivitySaveInstanceState(activity, outState)
    }

    override fun onActivityDestroyed(activity: Activity) {
        super.onActivityDestroyed(activity)

        mContainerFragmentMap.clear()
    }

    override fun onBackPressed(activity: Activity): Boolean = getFragmentManager(activity)?.let { fm ->
        if (fm.backStackEntryCount > 0) {
            closeDetail(fm)
            true
        } else false
    } ?: false

    override fun setNavigationIcon(icon: Int) {
        mNavigationIcon = icon
    }

    override fun setStartColorAnimation(color: Int) {
        mStartColorAnimation = color
    }

    override fun setEndColorAnimation(color: Int) {
        mEndColorAnimation = color
    }

    override fun setDurationAnimation(duration: Long) {
        mDurationAnimation = duration
    }

    private fun restoreCurrentFragmentType(savedInstanceState: Bundle?): FragmentType {
        return savedInstanceState?.getInt("currentFragmentType")?.let { FragmentType.values()[it] }
                ?: FragmentType.MASTER
    }

    private fun initFloatingActionButton(fm: FragmentManager): FloatingActionButton {
        return mCallback.onLoadFloatingActionButton().also {
            it.setOnClickListener {
                mContainerFragmentMap[FragmentType.DETAIL] = mCallback.onCreateFragment(FragmentType.DETAIL).also { fragment ->
                    fm.beginTransaction()
                            .replace(mContainerView.id, fragment, fragment.javaClass.simpleName)
                            .commit()
                    mCallback.onFragmentLoaded(FragmentType.DETAIL, fragment)
                }
            }
        }
    }

    private fun initToolbar(activity: AppCompatActivity, fm: FragmentManager): Toolbar {
        return mCallback.onCreateToolbarView().also { toolbar ->
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.setDisplayShowTitleEnabled(false)
            toolbar.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
            toolbar.setNavigationOnClickListener {
                if (fm.backStackEntryCount > 0) closeDetail(fm)
            }
            fm.addOnBackStackChangedListener {
                if (fm.backStackEntryCount > 0) {
                    mCurrentFragmentType = FragmentType.DETAIL
                    mOriginalNavigationIcon = toolbar.navigationIcon
                    toolbar.setNavigationIcon(mNavigationIcon)
                    mFloatingActionButton.hide()
                } else {
                    mCurrentFragmentType = FragmentType.MASTER
                    toolbar.navigationIcon = mOriginalNavigationIcon
                    mFloatingActionButton.show()
                }
            }
            mCallback.onToolbarViewCreated(toolbar)
        }
    }

    private fun closeDetail(fm: FragmentManager) {
        mContainerFragmentMap[FragmentType.DETAIL]?.view?.also { fragmentView ->
            startCircularRevealExitAnimation(fragmentView, object : AnimationFinishedListener {
                override fun onAnimationFinished() {
                    fm.popBackStack()
                }
            })
            mToolbar.navigationIcon = mOriginalNavigationIcon
        }
    }

    private fun getStartColorAnimation(): Int {
        return if (mStartColorAnimation != 0) mStartColorAnimation else mFloatingActionButton.backgroundTintList?.defaultColor
                ?: 0
    }

    private fun getEndColorAnimation(): Int {
        return if (mEndColorAnimation != 0) mEndColorAnimation else mFloatingActionButton.rippleColorStateList?.defaultColor
                ?: 0
    }

    private fun getDurationAnimation(resources: Resources): Long {
        return if (mDurationAnimation != 0.toLong()) mDurationAnimation else resources.getInteger(android.R.integer.config_longAnimTime).toLong()
    }

    private fun registerCircularRevealAnimation(view: View, listener: AnimationFinishedListener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                v.removeOnLayoutChangeListener(this)
                val cx = (mFloatingActionButton.x + mFloatingActionButton.width / 2).toInt()
                val cy = (mFloatingActionButton.y).toInt()
                val width = v.width
                val height = v.height

                //Simply use the diagonal of the view
                val finalRadius = Math.sqrt((width * width + height * height).toDouble()).toFloat()

                ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius).apply {
                    duration = getDurationAnimation(view.resources)
                    interpolator = FastOutSlowInInterpolator()
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            listener.onAnimationFinished()
                        }
                    })
                }.start()

                ValueAnimator().apply {
                    setIntValues(getStartColorAnimation(), getEndColorAnimation())
                    setEvaluator(ArgbEvaluator())
                    addUpdateListener { view.setBackgroundColor(it.animatedValue as Int) }
                    duration = getDurationAnimation(view.resources)
                }.start()
            }
        })
    }

    private fun startCircularRevealExitAnimation(view: View, listener: AnimationFinishedListener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        val cx = (mFloatingActionButton.x + mFloatingActionButton.width / 2).toInt()
        val cy = (mFloatingActionButton.y).toInt()
        val width = view.width
        val height = view.height

        val initRadius = Math.sqrt((width * width + height * height).toDouble()).toFloat()

        ViewAnimationUtils.createCircularReveal(view, cx, cy, initRadius, 0f).apply {
            duration = getDurationAnimation(view.resources)
            interpolator = FastOutSlowInInterpolator()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    //Important: This will prevent the view's flashing (visible between the finished animation and the Fragment remove)
                    view.visibility = View.GONE
                    listener.onAnimationFinished()
                }
            })
        }.start()

        ValueAnimator().apply {
            setIntValues(getEndColorAnimation(), getStartColorAnimation())
            setEvaluator(ArgbEvaluator())
            addUpdateListener { view.setBackgroundColor(it.animatedValue as Int) }
            duration = getDurationAnimation(view.resources)
        }.start()
    }
}

