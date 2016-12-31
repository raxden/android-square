package com.raxdenstudios.square.lifecycle;

import android.content.res.Configuration;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
public interface ApplicationLifecycle {

    void onConfigurationChanged(Configuration newConfig);

    void onCreate();

    void onLowMemory();

    void onTrimMemory(int level);

}
