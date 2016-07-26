package com.raxdenstudios.square.activity.interceptor;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.NetworkInterceptorDelegate;

/**
 * Created by agomez on 08/05/2015.
 */
public interface NetworkInterceptor extends Interceptor {

    void onInterceptorCreated(NetworkInterceptorDelegate callback);

    Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter);

    void unregisterReceiver(BroadcastReceiver receiver);

    void onWifiAvailable(boolean available);

    void onNetworkAvailable(boolean available);

}
