package com.raxdenstudios.square.interceptor.network;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 08/05/2015.
 */
public interface NetworkInterceptorCallback extends InterceptorCallback {

    void onWifiAvailable(boolean available);

    void onNetworkAvailable(boolean available);

}
