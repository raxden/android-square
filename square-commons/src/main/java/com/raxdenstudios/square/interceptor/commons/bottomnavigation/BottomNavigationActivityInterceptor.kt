package com.raxdenstudios.square.interceptor.commons.bottomnavigation

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class BottomNavigationActivityInterceptor<TFragment : Fragment>(
        callback: HasBottomNavigationInterceptor<TFragment>
) : ActivityInterceptor<BottomNavigationInterceptor, HasBottomNavigationInterceptor<TFragment>>(callback),
        BottomNavigationInterceptor {

    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var mContainerView: View
    private var mHasSavedInstanceState: Boolean = false
    private var mContainerFragmentMap: MutableMap<Int, TFragment?> = mutableMapOf()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? FragmentActivity)?.also {
            mHasSavedInstanceState = savedInstanceState != null
            mContainerView = mCallback.onLoadFragmentContainer()
            mBottomNavigationView = initBottomNavigationView(activity).also {
                for (position in 0 until it.childCount) {
                    val menuItemId = it.getChildAt(position).id

//                    it.selectedItemId
//                    mContainerFragmentMap[menuItemId] = instantiateFragment(activity, menuItemId)
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        super.onActivityStarted(activity)

        (activity as? FragmentActivity)?.also {

        }
    }

    private fun initBottomNavigationView(activity: FragmentActivity): BottomNavigationView = mCallback.onCreateBottomNavigationView().also {
        it.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
            }
        }
        mCallback.onBottomNavigationViewCreated(it)
    }

    private fun instantiateFragment(activity: FragmentActivity, menuItemId: Int): TFragment? {
        return if (!mHasSavedInstanceState) {
            mCallback.onCreateFragment(menuItemId).also {
                activity.supportFragmentManager.beginTransaction().replace(mContainerView.id, it, it.javaClass.simpleName).commit()
                mCallback.onFragmentLoaded(menuItemId, it)
            }
        } else if (mContainerFragmentMap[menuItemId] == null) {
            (activity.supportFragmentManager.findFragmentById(mContainerView.id) as? TFragment)?.also {
                mCallback.onFragmentLoaded(menuItemId, it)
            }
        } else {
            mContainerFragmentMap[menuItemId]
        }
    }
}

