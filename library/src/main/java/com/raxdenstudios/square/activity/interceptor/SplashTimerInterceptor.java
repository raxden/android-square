package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.activity.interceptor.impl.SplashTimerInterceptorImpl;

/**
 * Created by agomez on 11/05/2015.
 */
public interface SplashTimerInterceptor {

    void onInterceptorLoaded(SplashTimerInterceptorListener interceptor);
    void onSplashTimerEnd();
    void onSplashTimerCancelled();

    interface SplashTimerInterceptorListener {
        SplashTimerInterceptorImpl.SplashTimerConfiguration getConfiguration();
    }
}
