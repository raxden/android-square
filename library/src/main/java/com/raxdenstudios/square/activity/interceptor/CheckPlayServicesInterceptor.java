package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.callback.CheckPlayServicesInterceptorCallback;

/**
 * Created by agomez on 06/05/2015.
 */
public interface CheckPlayServicesInterceptor extends Interceptor {

    void onInterceptorCreated(CheckPlayServicesInterceptorCallback callback);

    void onGooglePlayServicesSupported();

    void onGooglePlayServicesNotSupported();

}
