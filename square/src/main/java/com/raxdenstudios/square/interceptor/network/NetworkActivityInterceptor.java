package com.raxdenstudios.square.interceptor.network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.utils.NetworkUtils;

/**
 * This interceptor requires the following permissions:
 * + android.permission.ACCESS_NETWORK_STATE
 *
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

    public NetworkActivityInterceptor(@NonNull Activity activity, @NonNull NetworkInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mActivity.registerReceiver(mNetworkReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mActivity.unregisterReceiver(mNetworkReceiver);
    }

}
