package com.raxdenstudios.square.application;

import com.raxdenstudios.square.application.module.LocaleModule;

/**
 * Created by agomez on 14/08/2015.
 */
public class LocaleApplication extends ModularApplication implements LocaleModule {

    private LocaleModuleListener mLocaleModule;

    @Override
    public void onLocaleModuleLoaded(LocaleModuleListener module) {
        mLocaleModule = module;
    }

    public LocaleModuleListener getLocaleModule() {
        return mLocaleModule;
    }

}
