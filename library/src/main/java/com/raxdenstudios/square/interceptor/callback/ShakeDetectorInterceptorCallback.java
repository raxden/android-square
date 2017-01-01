package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.ShakeDetectorInterceptorInteractor;

/**
 * Created by agomez on 06/05/2015.
 */
public interface ShakeDetectorInterceptorCallback
        extends InterceptorCallback<ShakeDetectorInterceptorInteractor> {

    void shakeDetected();

}
