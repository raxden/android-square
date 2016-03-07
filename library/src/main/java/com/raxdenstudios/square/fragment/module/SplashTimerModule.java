package com.raxdenstudios.square.fragment.module;

import com.raxdenstudios.square.fragment.module.impl.SplashTimerModuleImpl;

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
