package com.raxdenstudios.square

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.manager.ActivityInterceptorManager
import com.raxdenstudios.square.manager.InterceptorManagerFactory

/**
 * Created by Ángel Gómez
 *
 * SquareActivity is an abstract class that adds interceptor functionality to the activity.
 */
abstract class SquareActivity : AppCompatActivity() {

    private var activityInterceptorManager: ActivityInterceptorManager? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        interceptorManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        interceptorManager.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        interceptorManager.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        interceptorManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        interceptorManager.onNewIntent(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interceptorManager.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        interceptorManager.onPostCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        interceptorManager.onCreateOptionsMenu(menu)
        return super.onCreateOptionsMenu(menu)
    }

    /* Called whenever we call ActivityCompat.invalidateOptionsMenu(); */
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        interceptorManager.onPrepareOptionsMenu(menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        interceptorManager.onResume()
    }

    override fun onStart() {
        super.onStart()
        interceptorManager.onStart()
    }

    override fun onPause() {
        super.onPause()
        interceptorManager.onPause()
    }

    override fun onStop() {
        super.onStop()
        interceptorManager.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        interceptorManager.onDestroy()
    }

    override fun onBackPressed() {
        if (interceptorManager.onBackPressed()) return
        else super.onBackPressed()
    }

    // ========== Support methods ==================================================================

    protected abstract fun setupInterceptors(interceptorList: List<ActivityInterceptor<*>>)

    private val interceptorManager: ActivityInterceptorManager
        get() {
            return activityInterceptorManager ?: initInterceptorManager()
        }

    private fun initInterceptorManager(): ActivityInterceptorManager {
        activityInterceptorManager = InterceptorManagerFactory.buildManager(this) as ActivityInterceptorManager
        activityInterceptorManager?.apply {
            val interceptorList = mutableListOf<ActivityInterceptor<*>>()
            setupInterceptors(interceptorList)
            interceptorList.forEach { addInterceptor(it) }
        }
        return activityInterceptorManager!!
    }

}
