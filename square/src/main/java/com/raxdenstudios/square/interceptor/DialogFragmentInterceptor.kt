package com.raxdenstudios.square.interceptor

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
import com.raxdenstudios.square.lifecycle.DialogFragmentLifecycle

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an dialogFragment interceptor.
 */
abstract class DialogFragmentInterceptor<TCallback : InterceptorCallback>(
        protected var dialogFragment: DialogFragment,
        callback: TCallback? = null)
    : BaseInterceptor<TCallback>(dialogFragment, callback),
        DialogFragmentLifecycle {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onInterceptorCreated()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?, createdDialog: Dialog): Dialog = createdDialog

    override fun onCancel(dialog: DialogInterface?) {}

    override fun onDismiss(dialog: DialogInterface?) {}

    override fun onSaveInstanceState(outState: Bundle) {}

    override fun onViewStateRestored(savedInstanceState: Bundle) {}

    override fun onAttach(activity: Activity?) {}

    override fun onAttach(context: Context?) {}

    override fun onAttachFragment(childFragment: Fragment?) {}

    override fun onCreateView(inflater: LayoutInflater, inflatedView: View?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflatedView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {}

    override fun onStart() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {}

    override fun onDestroyView() {}

    override fun onDestroy() {
        super.onInterceptorDestroyed()
    }

    override fun onDetach() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}

    override fun onConfigurationChanged(configuration: Configuration?) {}

}
