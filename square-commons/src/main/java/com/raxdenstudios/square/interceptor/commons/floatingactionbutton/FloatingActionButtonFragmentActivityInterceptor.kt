package com.raxdenstudios.square.interceptor.commons.floatingactionbutton

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.R

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class FloatingActionButtonFragmentActivityInterceptor<TMasterFragment : Fragment, TDetailFragment : Fragment>(
        callback: HasFloatingActionButtonFragmentInterceptor<TMasterFragment, TDetailFragment>
) : ActivityInterceptor<FloatingActionButtonFragmentInterceptor, HasFloatingActionButtonFragmentInterceptor<TMasterFragment, TDetailFragment>>(callback),
        FloatingActionButtonFragmentInterceptor {

    private var mNavigationIcon: Int = R.drawable.square__ic_close_white_24dp
    private lateinit var mFloatingActionButton: FloatingActionButton
    private lateinit var mToolbar: Toolbar
    private lateinit var mFragmentContainer: View
    private lateinit var mMasterFragment: TMasterFragment
    private lateinit var mDetailFragment: TDetailFragment

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? AppCompatActivity)?.also {
            mFloatingActionButton = initFloatingActionButton(activity, savedInstanceState)
            mToolbar = initToolbar(activity, savedInstanceState)
            mFragmentContainer = mCallback.onLoadFragmentContainer()
            mMasterFragment = initMasterFragment(activity, savedInstanceState)
            mDetailFragment = initDetailFragment(activity, savedInstanceState)
        }
    }

    private fun initFloatingActionButton(activity: AppCompatActivity, savedInstanceState: Bundle?): FloatingActionButton {
        return mCallback.onLoadFloatingActionButton().also {
            it.setOnClickListener{
                activity.supportFragmentManager.beginTransaction().addToBackStack("detail").replace(mFragmentContainer.id, mDetailFragment).commit()
            }
        }
    }

    private fun initToolbar(activity: AppCompatActivity, savedInstanceState: Bundle?): Toolbar = mCallback.onCreateToolbarView(savedInstanceState).also {
        activity.setSupportActionBar(it)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        it.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
        it.setNavigationOnClickListener {
            if (activity.supportFragmentManager.backStackEntryCount > 0)
                activity.supportFragmentManager.popBackStack()
        }
        activity.supportFragmentManager.addOnBackStackChangedListener {
            if (activity.supportFragmentManager.backStackEntryCount > 0) {
                it.setNavigationIcon(mNavigationIcon)
                mFloatingActionButton.hide()
            } else {
                it.navigationIcon = null
                mFloatingActionButton.show()
            }
        }
        mCallback.onToolbarViewCreated(it)
    }

    private fun initMasterFragment(activity: AppCompatActivity, savedInstanceState: Bundle?): TMasterFragment {
        return mFragmentContainer.let { view ->
            savedInstanceState?.let {
                activity.supportFragmentManager.findFragmentById(view.id) as TMasterFragment
            } ?: mCallback.onCreateMasterFragment().also {
                activity.supportFragmentManager.beginTransaction().replace(view.id, it, it.javaClass.simpleName).commit()
            }
        }
    }

    private fun initDetailFragment(activity: AppCompatActivity, savedInstanceState: Bundle?): TDetailFragment {
        return mCallback.onCreateDetailFragment()
    }

}

