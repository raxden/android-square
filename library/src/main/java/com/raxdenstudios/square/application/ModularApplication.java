package com.raxdenstudios.square.application;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.application.module.manager.ModuleApplicationManager;

/**
 * Created by agomez on 13/07/2015.
 */
public class ModularApplication extends Application {

    /* Module Manager */
    private ModuleApplicationManager mModuleManager;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getModuleManager().onConfigurationChanged(getApplicationContext(), newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getModuleManager().onCreateModules(getApplicationContext());
    }

    private ModuleApplicationManager getModuleManager() {
        if (mModuleManager == null) {
            mModuleManager = new ModuleApplicationManager(this);
            initModules(mModuleManager);
        }
        return mModuleManager;
    }

    protected void initModules(ModuleApplicationManager moduleManager) {

    }

}
