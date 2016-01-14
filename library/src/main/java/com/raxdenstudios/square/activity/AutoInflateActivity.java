package com.raxdenstudios.square.activity;

import com.raxdenstudios.square.activity.module.AutoInflateLayoutModule;

/**
 * Created by agomez on 02/06/2015.
 */
public abstract class AutoInflateActivity extends ModularActivity implements AutoInflateLayoutModule {

    protected AutoInflateLayoutModuleListener mAutoInflateLayoutModule;

    @Override
    public void onModuleLoaded(AutoInflateLayoutModuleListener module) {
        mAutoInflateLayoutModule = module;
    }

}
