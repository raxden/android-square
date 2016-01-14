package com.raxdenstudios.square.application.module.manager;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.raxdenstudios.square.application.ModularApplication;
import com.raxdenstudios.square.application.ModularMultiDexApplication;
import com.raxdenstudios.square.application.module.LocaleModule;
import com.raxdenstudios.square.application.module.impl.LocaleModuleImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 13/07/2015.
 */
public class ModuleApplicationManager {

    private static final String TAG = ModuleApplicationManager.class.getSimpleName();

    protected List<ModuleApplication> modules;

    public ModuleApplicationManager(ModularApplication application) {
        if (application != null) initModules(application);
    }

    public ModuleApplicationManager(ModularMultiDexApplication application) {
        if (application != null) initModules(application);
    }

    public void onConfigurationChanged(Context context, Configuration newConfig) {
        if (modules != null) {
            for (ModuleApplication module : modules) {
                module.onConfigurationChanged(context, newConfig);
            }
        }
    }

    public void onCreateModules(Context context) {
        if (modules != null) {
            for (ModuleApplication module : modules) {
                module.onCreate(context);
            }
        }
    }

    public void addModule(ModuleApplication module) {
        modules.add(module);
    }

    public List<ModuleApplication> getModules() {
        return modules;
    }

    public void setModules(List<ModuleApplication> modules) {
        this.modules = modules;
    }


    private void initModules(ModularApplication application) {
        modules = new ArrayList<ModuleApplication>();
        if (application instanceof LocaleModule) {
            Log.d(TAG, "[initModules] LocaleModule loaded!");
            modules.add(new LocaleModuleImpl(application));
        }
    }

    private void initModules(ModularMultiDexApplication application) {
        modules = new ArrayList<ModuleApplication>();
        if (application instanceof LocaleModule) {
            Log.d(TAG, "[initModules] LocaleModule loaded!");
            modules.add(new LocaleModuleImpl(application));
        }
    }

}
