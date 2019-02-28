package com.raxdenstudios.square.interceptor.commons.bottomnavigation

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
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
    private var mContainerFragmentMap: MutableMap<Int, TFragment?> = mutableMapOf()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            mContainerView = mCallback.onLoadFragmentContainer()
            mBottomNavigationView = initBottomNavigationView(fm)

            if (savedInstanceState == null) {
                mBottomNavigationView.selectedItemId.also { id ->
                    mContainerFragmentMap[id] = mCallback.onCreateFragment(id).also {
                        fm.beginTransaction()
                                .replace(mContainerView.id, it, "fragment_$id")
                                .commit()
                        mCallback.onFragmentLoaded(id, it)
                    }
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        super.onActivityStarted(activity)

        getFragmentManager(activity)?.also { fm ->
            mSavedInstanceState?.also { bundle ->
                bundle.getIntArray("itemIds")?.forEach { itemId ->
                    mContainerFragmentMap[itemId] = (fm.findFragmentByTag("fragment_$itemId") as? TFragment)?.also {
                        mCallback.onFragmentLoaded(itemId, it)
                    }
                }
            }
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        outState?.putInt("selectedItemId", mBottomNavigationView.selectedItemId)
        outState?.putIntArray("itemIds", mContainerFragmentMap.keys.toIntArray())

        super.onActivitySaveInstanceState(activity, outState)
    }

    private fun initBottomNavigationView(fm: FragmentManager): BottomNavigationView {
        return mCallback.onCreateBottomNavigationView().also { view ->
            mSavedInstanceState?.getInt("selectedItemId")?.also { id ->
                view.selectedItemId = id
            }
            view.setOnNavigationItemSelectedListener { menuItem ->
                mContainerFragmentMap[view.selectedItemId]?.also { activeFragment ->
                    val fragment = mContainerFragmentMap[menuItem.itemId]?.also {
                        fm.beginTransaction()
                                .hide(activeFragment)
                                .show(it)
                                .commit()
                    } ?: mCallback.onCreateFragment(menuItem.itemId).also {
                        fm.beginTransaction()
                                .add(mContainerView.id, it, "fragment_${menuItem.itemId}")
                                .hide(activeFragment)
                                .commit()
                    }
                    mContainerFragmentMap[menuItem.itemId] = fragment
                    mCallback.onFragmentLoaded(menuItem.itemId, fragment)
                }
                mCallback.onMenuItemSelected(menuItem.itemId)
                true
            }
            mCallback.onBottomNavigationViewCreated(view)
        }
    }
}

