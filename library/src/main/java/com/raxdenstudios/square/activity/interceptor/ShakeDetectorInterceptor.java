package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by agomez on 06/05/2015.
 */
public interface ShakeDetectorInterceptor extends Interceptor<ShakeDetectorInterceptor.ShakeDetectorInterceptorCallback> {

    void shakeDetected();

    interface ShakeDetectorInterceptorCallback extends InterceptorCallback {

    }
}
