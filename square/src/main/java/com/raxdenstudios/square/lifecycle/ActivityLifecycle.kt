package com.raxdenstudios.square.lifecycle

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the Activity life cycle used by interceptors.
 */
interface ActivityLifecycle {

    fun onSaveInstanceState(outState: Bundle?)

    fun onRestoreInstanceState(savedInstanceState: Bundle)

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)

    fun onNewIntent(intent: Intent?)

    fun onCreate(savedInstanceState: Bundle?)

    fun onPostCreate(savedInstanceState: Bundle?)

    fun onCreateOptionsMenu(menu: Menu?)

    fun onPrepareOptionsMenu(menu: Menu?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun onBackPressed(): Boolean

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun onConfigurationChanged(configuration: Configuration?)

}
