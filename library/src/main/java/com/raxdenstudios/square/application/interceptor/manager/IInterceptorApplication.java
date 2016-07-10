package com.raxdenstudios.square.application.interceptor.manager;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by agomez on 13/07/2015.
 */
public interface IInterceptorApplication {

    void onConfigurationChanged(Context context, Configuration newConfig);
    void onCreate(Context context);

}
