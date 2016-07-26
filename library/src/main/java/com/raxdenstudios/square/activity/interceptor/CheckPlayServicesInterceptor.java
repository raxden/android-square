package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.CheckPlayServicesInterceptorDelegate;

/**
 * Created by agomez on 06/05/2015.
 */
public interface CheckPlayServicesInterceptor extends Interceptor {

    void onInterceptorCreated(CheckPlayServicesInterceptorDelegate callback);

    void onGooglePlayServicesSupported();

    void onGooglePlayServicesNotSupported();

}
