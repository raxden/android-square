package com.raxdenstudios.square.interceptor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raxdenstudios.square.lifecycle.FragmentLifecycle

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an fragment interceptor.
 */
abstract class FragmentInterceptor<TCallback : InterceptorCallback>(
        protected var fragment: Fragment,
        callback: TCallback? = null)
    : BaseInterceptor<TCallback>(fragment, callback),
        FragmentLifecycle {

    override fun onSaveInstanceState(outState: Bundle) {}

    override fun onViewStateRestored(savedInstanceState: Bundle) {}

    override fun onAttach(activity: Activity?) {}

    override fun onAttach(context: Context?) {}

    override fun onAttachFragment(childFragment: Fragment?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onInterceptorCreated()
    }

    override fun onCreateView(inflater: LayoutInflater, inflatedView: View?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflatedView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {}

    override fun onStart() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {}

    override fun onDestroy() {
        super.onInterceptorDestroyed()
    }

    override fun onDestroyView() {}

    override fun onDetach() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}

    override fun onConfigurationChanged(configuration: Configuration?) {}

}
