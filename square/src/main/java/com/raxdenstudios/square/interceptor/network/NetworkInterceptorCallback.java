package com.raxdenstudios.square.interceptor.network;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 08/05/2015.
 */
public interface NetworkInterceptorCallback
        extends InterceptorCallback<NetworkInteractor> {

    Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter);

    void unregisterReceiver(BroadcastReceiver receiver);

    void onWifiAvailable(boolean available);

    void onNetworkAvailable(boolean available);

}
