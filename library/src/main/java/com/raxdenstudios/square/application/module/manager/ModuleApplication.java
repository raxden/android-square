package com.raxdenstudios.square.application.module.manager;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by agomez on 13/07/2015.
 */
public interface ModuleApplication {

    void onConfigurationChanged(Context context, Configuration newConfig);
    void onCreate(Context context);

}
