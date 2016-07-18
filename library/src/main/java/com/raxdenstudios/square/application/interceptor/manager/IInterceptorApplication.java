package com.raxdenstudios.square.application.interceptor.manager;

import android.content.res.Configuration;

/**
 * Created by agomez on 13/07/2015.
 */
public interface IInterceptorApplication {

    void onConfigurationChanged(Configuration newConfig);

    void onCreate();

}
