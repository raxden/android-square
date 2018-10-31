package com.raxdenstudios.square

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor
import com.raxdenstudios.square.manager.DialogFragmentInterceptorManager
import com.raxdenstudios.square.manager.InterceptorManagerFactory

/**
 * Created by Ángel Gómez
 *
 * SquareDialogFragment is an abstract class that adds interceptor functionality to the
 * DialogFragment.
 */
abstract class SquareDialogFragment : DialogFragment() {

    private var dialogFragmentInterceptorManager: DialogFragmentInterceptorManager? = null

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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return interceptorManager.onCreateDialog(savedInstanceState, dialog)
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        interceptorManager.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        interceptorManager.onDismiss(dialog)
    }

    /* Support methods */

    protected abstract fun setupInterceptors(interceptorList: List<DialogFragmentInterceptor<*>>)

    private val interceptorManager: DialogFragmentInterceptorManager
        get() {
            return dialogFragmentInterceptorManager ?: initInterceptorManager()
        }

    private fun initInterceptorManager(): DialogFragmentInterceptorManager {
        dialogFragmentInterceptorManager = InterceptorManagerFactory.buildManager(this) as DialogFragmentInterceptorManager
        dialogFragmentInterceptorManager?.apply {
            val interceptorList = mutableListOf<DialogFragmentInterceptor<*>>()
            setupInterceptors(interceptorList)
            interceptorList.forEach { addInterceptor(it) }
        }
        return dialogFragmentInterceptorManager!!
    }

}
