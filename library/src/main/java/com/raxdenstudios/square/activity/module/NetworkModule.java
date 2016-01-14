package com.raxdenstudios.square.activity.module;

/**
 * Created by agomez on 08/05/2015.
 */
public interface NetworkModule {
    void onWifiAvailable(boolean available);
    void onNetworkAvailable(boolean available);
}
