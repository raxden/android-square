package com.raxdenstudios.square.activity.module;

import com.raxdenstudios.square.activity.module.impl.SplashTimerModuleImpl;

/**
 * Created by agomez on 11/05/2015.
 */
public interface SplashTimerModule {

    void onModuleLoaded(SplashTimerModuleListener module);
    void onSplashTimerEnd();
    void onSplashTimerCancelled();

    interface SplashTimerModuleListener {
        SplashTimerModuleImpl.SplashTimerConfiguration getConfiguration();
    }
}
