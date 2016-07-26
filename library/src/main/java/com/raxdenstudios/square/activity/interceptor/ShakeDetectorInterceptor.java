package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.ShakeDetectorInterceptorDelegate;

/**
 * Created by agomez on 06/05/2015.
 */
public interface ShakeDetectorInterceptor extends Interceptor {

    void onInterceptorCreated(ShakeDetectorInterceptorDelegate callback);

    void shakeDetected();

}
