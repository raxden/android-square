package com.raxdenstudios.square.interceptor.network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.raxdenstudios.commons.util.NetworkUtils;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by agomez on 08/05/2015.
 */
public class NetworkActivityInterceptor
        extends ActivityInterceptor<NetworkInteractor, NetworkInterceptorCallback>
        implements NetworkInteractor {

    private BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mCallback != null) {
                mCallback.onNetworkAvailable(NetworkUtils.isNetworkAvailable(context));
                mCallback.onWifiAvailable(NetworkUtils.isWifiAvailable(context));
            }
        }
    };

    public NetworkActivityInterceptor(Activity activity, NetworkInterceptorCallback callback) {
        super(activity, callback);
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
