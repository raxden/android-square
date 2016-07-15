package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 08/05/2015.
 */
public interface NetworkInterceptor extends Interceptor {

    void onWifiAvailable(boolean available);

    void onNetworkAvailable(boolean available);

    interface NetworkInterfaceCallback {

    }
}
