package com.raxdenstudios.square.interceptor.commons.injectfragmentlist

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class InjectFragmentListActivityInterceptor<TFragment : Fragment>(
        callback: HasInjectFragmentListInterceptor<TFragment>
) : ActivityInterceptor<InjectFragmentListInterceptor, HasInjectFragmentListInterceptor<TFragment>>(callback),
        InjectFragmentListInterceptor {

    private var mContainerViewMap: MutableMap<Int, View> = mutableMapOf()
    private var mContainerFragmentMap: MutableMap<Int, TFragment?> = mutableMapOf()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        getFragmentManager(activity)?.also { fm ->
            if (savedInstanceState == null) {
                for (position in 0 until mCallback.fragmentCount) {
                    mContainerViewMap[position] = mCallback.onLoadFragmentContainer(position).also { view ->
                        mContainerFragmentMap[position] = mCallback.onCreateFragment(position).also {
                            fm.beginTransaction()
                                    .replace(view.id, it, it.javaClass.simpleName)
                                    .commit()
                            mCallback.onFragmentLoaded(position, it)
                        }
                    }
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        super.onActivityStarted(activity)

        getFragmentManager(activity)?.also { fm ->
            if (mSavedInstanceState != null) {
                for (position in 0 until mCallback.fragmentCount) {
                    mContainerViewMap[position]?.also { view ->
                        mContainerFragmentMap[position] = (fm.findFragmentById(view.id) as? TFragment)?.also {
                            mCallback.onFragmentLoaded(position, it)
                        }
                    }
                }
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        super.onActivityDestroyed(activity)

        mContainerViewMap.clear()
        mContainerFragmentMap.clear()
    }
}

