package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.impl.SplashTimerInterceptorImpl;

/**
 * Created by agomez on 11/05/2015.
 */
public interface SplashTimerInterceptor extends Interceptor<SplashTimerInterceptor.SplashTimerInterceptorCallback> {

    void onSplashTimerEnd();
    void onSplashTimerCancelled();

    interface SplashTimerInterceptorCallback extends InterceptorCallback {
        SplashTimerInterceptorImpl.SplashTimerConfiguration getConfiguration();
    }
}
