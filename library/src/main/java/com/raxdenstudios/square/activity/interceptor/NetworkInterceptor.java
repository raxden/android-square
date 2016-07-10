package com.raxdenstudios.square.activity.interceptor;

/**
 * Created by agomez on 08/05/2015.
 */
public interface NetworkInterceptor {
    void onWifiAvailable(boolean available);
    void onNetworkAvailable(boolean available);
}
