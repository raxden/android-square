package com.raxdenstudios.square.activity.module.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.raxdenstudios.commons.util.NetworkUtils;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.NetworkModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 08/05/2015.
 */
public class NetworkModuleImpl extends ModuleActivityImpl {

    private static final String TAG = NetworkModuleImpl.class.getSimpleName();

    private NetworkModule mCallbacks;

    protected BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mCallbacks != null) {
                mCallbacks.onNetworkAvailable(NetworkUtils.isNetworkAvailable(context));
                mCallbacks.onWifiAvailable(NetworkUtils.isWifiAvailable(context));
            }
        }
    };

    public NetworkModuleImpl(ModularActivity activity) {
        if (!(activity instanceof NetworkModule)) {
            throw new IllegalStateException("Activity must implement NetworkModule.");
        }
        mCallbacks = (NetworkModule)activity;        
    }

    @Override
    public void onModuleCreate(Context context, Bundle bundle) {
        super.onModuleCreate(context, bundle);

        if (mNetworkReceiver != null) context.registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onModuleDestroy(Context context) {
        super.onModuleDestroy(context);

        if (mNetworkReceiver != null) context.unregisterReceiver(mNetworkReceiver);
    }

}
