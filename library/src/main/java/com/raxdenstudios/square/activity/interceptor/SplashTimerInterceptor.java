package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.activity.interceptor.impl.SplashTimerInterceptorImpl;

/**
 * Created by agomez on 11/05/2015.
 */
public interface SplashTimerInterceptor {

    void onInterceptorLoaded(SplashTimerInterceptorCallback callback);

    void onSplashTimerEnd();

    void onSplashTimerCancelled();

    interface SplashTimerInterceptorCallback {
        SplashTimerInterceptorImpl.SplashTimerConfiguration getConfiguration();
    }
}
