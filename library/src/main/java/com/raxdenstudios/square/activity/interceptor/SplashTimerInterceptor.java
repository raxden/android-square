package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.impl.SplashTimerInterceptorImpl;

/**
 * Created by agomez on 11/05/2015.
 */
public interface SplashTimerInterceptor extends Interceptor {

    void onSplashTimerEnd();

    void onSplashTimerCancelled();

    interface SplashTimerInterceptorCallback {
        SplashTimerInterceptorImpl.SplashTimerConfiguration getConfiguration();
    }
}
