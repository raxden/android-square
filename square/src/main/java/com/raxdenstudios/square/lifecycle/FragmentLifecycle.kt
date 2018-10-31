package com.raxdenstudios.square.lifecycle

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the Fragment life cycle used by interceptors.
 */
interface FragmentLifecycle {

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle)

    fun onAttach(activity: Activity?)

    fun onAttach(context: Context?)

    fun onAttachFragment(childFragment: Fragment?)

    fun onCreate(savedInstanceState: Bundle?)

    fun onCreateView(inflater: LayoutInflater, inflatedView: View?, container: ViewGroup?, savedInstanceState: Bundle?): View?

    fun onViewCreated(view: View, savedInstanceState: Bundle?)

    fun onActivityCreated(savedInstanceState: Bundle?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun onDestroyView()

    fun onDetach()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun onConfigurationChanged(configuration: Configuration?)

}
