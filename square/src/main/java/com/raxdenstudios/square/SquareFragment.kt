package com.raxdenstudios.square

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.FragmentInterceptor
import com.raxdenstudios.square.manager.FragmentInterceptorManager
import com.raxdenstudios.square.manager.InterceptorManagerFactory

/**
 * Created by Ángel Gómez
 *
 * SquareFragment is an abstract class that adds interceptor functionality to the Fragment.
 */
abstract class SquareFragment : Fragment() {

    private var fragmentInterceptorManager: FragmentInterceptorManager? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        interceptorManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        interceptorManager.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        interceptorManager.onSaveInstanceState(outState)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        interceptorManager.onAttach(activity)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        interceptorManager.onAttach(context)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        interceptorManager.onAttachFragment(childFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interceptorManager.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        return interceptorManager.onCreateView(inflater, parentView, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        interceptorManager.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        interceptorManager.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        interceptorManager.onStart()
    }

    override fun onResume() {
        super.onResume()
        interceptorManager.onResume()
    }

    override fun onPause() {
        super.onPause()
        interceptorManager.onPause()
    }

    override fun onStop() {
        super.onStop()
        interceptorManager.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        interceptorManager.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        interceptorManager.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        interceptorManager.onDetach()
    }

    /* Support methods */

    protected abstract fun setupInterceptors(interceptorList: List<FragmentInterceptor<*>>)

    private val interceptorManager: FragmentInterceptorManager
        get() {
            return fragmentInterceptorManager ?: initInterceptorManager()
        }

    private fun initInterceptorManager(): FragmentInterceptorManager {
        fragmentInterceptorManager = InterceptorManagerFactory.buildManager(this) as FragmentInterceptorManager
        fragmentInterceptorManager?.apply {
            val interceptorList = mutableListOf<FragmentInterceptor<*>>()
            setupInterceptors(interceptorList)
            interceptorList.forEach { addInterceptor(it) }
        }
        return fragmentInterceptorManager!!
    }

}
