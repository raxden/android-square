package com.raxdenstudios.square.interceptor

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Menu

import com.raxdenstudios.square.lifecycle.ActivityLifecycle

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an activity interceptor.
 */
abstract class ActivityInterceptor<TCallback : InterceptorCallback>(
        protected var activity: FragmentActivity,
        callback: TCallback? = null)
    : BaseInterceptor<TCallback>(activity, callback),
        ActivityLifecycle {

    override fun onSaveInstanceState(outState: Bundle?) {}

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {}

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {}

    override fun onNewIntent(intent: Intent?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onInterceptorCreated()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {}

    override fun onCreateOptionsMenu(menu: Menu?) {}

    override fun onPrepareOptionsMenu(menu: Menu?) {}

    override fun onStart() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {}

    override fun onDestroy() {
        super.onInterceptorDestroyed()
    }

    override fun onBackPressed(): Boolean = false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}

    override fun onConfigurationChanged(configuration: Configuration?) {}

}
