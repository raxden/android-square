package com.raxdenstudios.square.activity.interceptor;

/**
 * Created by agomez on 06/05/2015.
 */
public interface CheckPlayServicesInterceptor {

    void onInterceptorLoaded(CheckPlayServicesInterceptorCallback callback);

    void onGooglePlayServicesSupported();

    void onGooglePlayServicesNotSupported();

    interface CheckPlayServicesInterceptorCallback {

    }
}
