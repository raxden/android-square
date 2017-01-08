package com.raxdenstudios.square.lifecycle;

import android.content.res.Configuration;

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the Application life cycle used by interceptors.
 */
public interface ApplicationLifecycle {

    void onConfigurationChanged(Configuration newConfig);

    void onCreate();

    void onLowMemory();

    void onTrimMemory(int level);

}
