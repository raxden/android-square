package com.raxdenstudios.square.interceptor.commons.network

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * This interceptor requires the following permissions:
 * + android.permission.ACCESS_NETWORK_STATE
 *
 * Created by agomez on 08/05/2015.
 */
class NetworkActivityInterceptor(
        callback: HasNetworkInterceptor
) : ActivityInterceptor<NetworkInterceptor, HasNetworkInterceptor>(callback),
        NetworkInterceptor {

    private val mNetworkBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            mCallback.onNetworkAvailable(isNetworkAvailable(context))
            mCallback.onWifiAvailable(isWifiAvailable(context))
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        activity?.apply {
            registerReceiver(mNetworkBroadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        activity?.apply {
            unregisterReceiver(mNetworkBroadcastReceiver)
        }
        super.onActivityDestroyed(activity)
    }

    private fun isNetworkAvailable(context: Context): Boolean = when (getConnectionType(context)) {
        1 -> true
        2 -> true
        else -> false
    }

    private fun isWifiAvailable(context: Context): Boolean = when (getConnectionType(context)) {
        2 -> true
        else -> false
    }

    @SuppressLint("MissingPermission")
    private fun getConnectionType(context: Context): Int {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.getNetworkCapabilities(cm.activeNetwork).run {
                if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return 2
                else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return 1
            }
        } else {
            cm.activeNetworkInfo.run {
                if (type == ConnectivityManager.TYPE_WIFI) return 2
                else if (type == ConnectivityManager.TYPE_MOBILE) return 1
            }
        }
        return 0
    }
}
