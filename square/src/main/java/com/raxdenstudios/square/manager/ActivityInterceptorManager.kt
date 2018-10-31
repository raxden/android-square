package com.raxdenstudios.square.manager

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Menu

import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.lifecycle.ActivityLifecycle

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

class ActivityInterceptorManager(activity: FragmentActivity) : InterceptorManager<FragmentActivity, ActivityInterceptor<*>>(activity), ActivityLifecycle {

    override fun onSaveInstanceState(outState: Bundle?) {
        interceptors.forEach { it.onSaveInstanceState(outState) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        interceptors.forEach { it.onRestoreInstanceState(savedInstanceState) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        interceptors.forEach { it.onRequestPermissionsResult(requestCode, permissions, grantResults) }
    }

    override fun onNewIntent(intent: Intent?) {
        interceptors.forEach { it.onNewIntent(intent) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        interceptors.forEach { it.onCreate(savedInstanceState) }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        interceptors.forEach { it.onPostCreate(savedInstanceState) }
    }

    override fun onCreateOptionsMenu(menu: Menu?) {
        interceptors.forEach { it.onCreateOptionsMenu(menu) }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        interceptors.forEach { it.onPrepareOptionsMenu(menu) }
    }

    override fun onStart() {
        interceptors.forEach { it.onStart() }
    }

    override fun onResume() {
        interceptors.forEach { it.onResume() }
    }

    override fun onPause() {
        interceptors.forEach { it.onPause() }
    }

    override fun onStop() {
        interceptors.forEach { it.onStop() }
    }

    override fun onDestroy() {
        interceptors.forEach { it.onDestroy() }
    }

    override fun onBackPressed(): Boolean {
        interceptors.forEach {
            if (it.onBackPressed()) return true
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        interceptors.forEach { it.onActivityResult(requestCode, resultCode, data) }
    }

    override fun onConfigurationChanged(configuration: Configuration?) {
        interceptors.forEach { it.onConfigurationChanged(configuration) }
    }

}
