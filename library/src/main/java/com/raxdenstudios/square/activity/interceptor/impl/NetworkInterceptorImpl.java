package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.raxdenstudios.commons.util.NetworkUtils;
import com.raxdenstudios.square.activity.interceptor.NetworkInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.NetworkInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 08/05/2015.
 */
public class NetworkInterceptorImpl extends InterceptorActivityImpl
        implements NetworkInterceptorCallback {

    private static final String TAG = NetworkInterceptorImpl.class.getSimpleName();

    private NetworkInterceptor mCallbacks;

    protected BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mCallbacks != null) {
                mCallbacks.onNetworkAvailable(NetworkUtils.isNetworkAvailable(context));
                mCallbacks.onWifiAvailable(NetworkUtils.isWifiAvailable(context));
            }
        }
    };

    public NetworkInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (NetworkInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mCallbacks.registerReceiver(mNetworkReceiver, intentFilter);
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();

        mCallbacks.unregisterReceiver(mNetworkReceiver);
    }

}
