package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.raxdenstudios.commons.util.NetworkUtils;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.NetworkInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.NetworkInterceptorConfig;

/**
 * Created by agomez on 08/05/2015.
 */
public class NetworkInterceptorImpl
        extends ActivityInterceptor<NetworkInterceptorConfig, NetworkInterceptorCallback>
        implements NetworkInterceptorConfig {

    private BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mCallback != null) {
                mCallback.onNetworkAvailable(NetworkUtils.isNetworkAvailable(context));
                mCallback.onWifiAvailable(NetworkUtils.isWifiAvailable(context));
            }
        }
    };

    public NetworkInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mCallback.registerReceiver(mNetworkReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mCallback.unregisterReceiver(mNetworkReceiver);
    }

}
