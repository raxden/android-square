package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.ShakeDetectorInterceptorConfig;

/**
 * Created by agomez on 06/05/2015.
 */
public interface ShakeDetectorInterceptorCallback
        extends InterceptorCallback<ShakeDetectorInterceptorConfig> {

    void shakeDetected();

}
