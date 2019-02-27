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

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class FloatingActionButtonFragmentActivityInterceptor<TFragment : Fragment>(
        callback: HasFloatingActionButtonFragmentInterceptor<TFragment>
) : ActivityInterceptor<FloatingActionButtonFragmentInterceptor, HasFloatingActionButtonFragmentInterceptor<TFragment>>(callback),
        FloatingActionButtonFragmentInterceptor {

    private var mNavigationIcon: Int = R.drawable.square__ic_close_white_24dp
    private var mStartColorAnimation: Int = 0
    private var mEndColorAnimation: Int = 0
    private var mDurationAnimation: Int = 0

    private lateinit var mFloatingActionButton: FloatingActionButton
    private lateinit var mToolbar: Toolbar
    private lateinit var mContainerView: View
    private var mHasSavedInstanceState: Boolean = false
    private var mCurrentFragmentType: FragmentType = FragmentType.MASTER
    private var mContainerFragmentMap: MutableMap<FragmentType, TFragment?> = mutableMapOf()

    interface AnimationFinishedListener {
        fun onAnimationFinished()
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? AppCompatActivity)?.also {
            mHasSavedInstanceState = savedInstanceState != null
            mCurrentFragmentType = savedInstanceState?.getInt("currentFragmentType")?.let { FragmentType.values()[it] }
                    ?: FragmentType.MASTER
            mFloatingActionButton = initFloatingActionButton(activity)
            mToolbar = initToolbar(activity)
            mContainerView = mCallback.onLoadFragmentContainer()
            mContainerFragmentMap[mCurrentFragmentType] = instantiateFragment(activity, mCurrentFragmentType)
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {

                override fun onFragmentViewCreated(fm: FragmentManager, fragment: Fragment, view: View, savedInstanceState: Bundle?) {
                    if (mContainerFragmentMap[FragmentType.DETAIL] == fragment)
                        registerCircularRevealAnimation(view, getStartColorAnimation(), getEndColorAnimation(), getDurationAnimation(activity.resources))
                }
            }, true)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        outState?.putInt("currentFragmentType", mCurrentFragmentType.ordinal)

        super.onActivitySaveInstanceState(activity, outState)
    }

    override fun onActivityStarted(activity: Activity?) {
        super.onActivityStarted(activity)

        (activity as? AppCompatActivity)?.also {
            if (activity.supportFragmentManager.backStackEntryCount > 0) {
                mToolbar.setNavigationIcon(mNavigationIcon)
                mFloatingActionButton.hide()
            } else {
                mToolbar.navigationIcon = null
                mFloatingActionButton.show()
            }
            mContainerFragmentMap[mCurrentFragmentType] = instantiateFragment(activity, mCurrentFragmentType)
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        mContainerFragmentMap.clear()

        super.onActivityDestroyed(activity)
    }

    override fun setNavigationIcon(icon: Int) {
        mNavigationIcon = icon
    }

    override fun setStartColorAnimation(color: Int) {
        mStartColorAnimation = color
    }

    override fun setEndColorAnimation(color: Int) {
        mEndColorAnimation = color
    }

    override fun setDurationAnimation(duration: Int) {
        mDurationAnimation = duration
    }

    private fun initFloatingActionButton(activity: AppCompatActivity): FloatingActionButton {
        return mCallback.onLoadFloatingActionButton().also {
            it.setOnClickListener {
                setFragment(activity, FragmentType.DETAIL)
            }
        }
    }

    private fun setFragment(activity: AppCompatActivity, fragmentType: FragmentType) {
        when(fragmentType) {
            FragmentType.MASTER -> {

            }
            FragmentType.DETAIL -> {

            }
        }
        mContainerFragmentMap[fragmentType] = mCallback.onCreateFragment(fragmentType).also { fragment ->
            activity.supportFragmentManager.beginTransaction().replace(mContainerView.id, fragment, fragment.javaClass.simpleName).commit()
            mCallback.onFragmentLoaded(fragmentType, fragment)
        }
        mCurrentFragmentType = fragmentType
    }

    private fun initToolbar(activity: AppCompatActivity): Toolbar = mCallback.onCreateToolbarView().also {
        activity.setSupportActionBar(it)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        it.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
        it.setNavigationOnClickListener {
            if (activity.supportFragmentManager.backStackEntryCount > 0)
                activity.supportFragmentManager.popBackStack()
        }
        activity.supportFragmentManager.addOnBackStackChangedListener {
            if (activity.supportFragmentManager.backStackEntryCount > 0) {
                mCurrentFragmentType = FragmentType.DETAIL
                it.setNavigationIcon(mNavigationIcon)
                mFloatingActionButton.hide()
            } else {
                mCurrentFragmentType = FragmentType.MASTER
                it.navigationIcon = null
                mFloatingActionButton.show()
            }
        }
        mCallback.onToolbarViewCreated(it)
    }

    private fun instantiateFragment(activity: AppCompatActivity, fragmentType: FragmentType): TFragment? {
        return if (!mHasSavedInstanceState) {
            mCallback.onCreateFragment(fragmentType).also {
                activity.supportFragmentManager.beginTransaction().replace(mContainerView.id, it, it.javaClass.simpleName).commit()
                mCallback.onFragmentLoaded(fragmentType, it)
            }
        } else if (mContainerFragmentMap[fragmentType] == null) {
            (activity.supportFragmentManager.findFragmentById(mContainerView.id) as? TFragment)?.also {
                mCallback.onFragmentLoaded(fragmentType, it)
            }
        } else {
            mContainerFragmentMap[fragmentType]
        }
    }

    private fun getStartColorAnimation(): Int {
        return if (mStartColorAnimation != 0) mStartColorAnimation else mFloatingActionButton.backgroundTintList?.defaultColor
                ?: 0
    }

    private fun getEndColorAnimation(): Int {
        return if (mEndColorAnimation != 0) mEndColorAnimation else mFloatingActionButton.rippleColor
    }

    private fun getDurationAnimation(resources: Resources): Int {
        return if (mDurationAnimation != 0) mDurationAnimation else resources.getInteger(android.R.integer.config_mediumAnimTime)
    }

    private fun registerCircularRevealAnimation(view: View, animStartColor: Int, animEndColor: Int, animDuration: Int) {
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
                    duration = animDuration.toLong()
                    interpolator = FastOutSlowInInterpolator()
                }.start()

                ValueAnimator().apply {
                    setIntValues(animStartColor, animEndColor)
                    setEvaluator(ArgbEvaluator())
                    addUpdateListener { view.setBackgroundColor(it.animatedValue as Int) }
                    duration = animDuration.toLong()
                }.start()
            }
        })
    }

    private fun startCircularRevealExitAnimation(view: View, animStartColor: Int, animEndColor: Int, animDuration: Int/*, listener: AnimationFinishedListener*/) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        val cx = (mFloatingActionButton.x + mFloatingActionButton.width / 2).toInt()
        val cy = (mFloatingActionButton.y).toInt()
        val width = view.width
        val height = view.height

        val initRadius = Math.sqrt((width * width + height * height).toDouble()).toFloat()

        ViewAnimationUtils.createCircularReveal(view, cx, cy, initRadius, 0f).apply {
            duration = animDuration.toLong()
            interpolator = FastOutSlowInInterpolator()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    //Important: This will prevent the view's flashing (visible between the finished animation and the Fragment remove)
                    view.visibility = View.GONE
//                    listener.onAnimationFinished()
                }
            })
        }.start()

        ValueAnimator().apply {
            setIntValues(animStartColor, animEndColor)
            setEvaluator(ArgbEvaluator())
            addUpdateListener { view.setBackgroundColor(it.animatedValue as Int) }
            duration = animDuration.toLong()
        }.start()
    }
}

