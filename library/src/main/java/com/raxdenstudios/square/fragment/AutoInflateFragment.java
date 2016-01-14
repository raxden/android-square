package com.raxdenstudios.square.fragment;

import com.raxdenstudios.square.fragment.module.AutoInflateViewModule;

/**
 * Created by agomez on 03/06/2015.
 */
public abstract class AutoInflateFragment extends ModularFragment implements AutoInflateViewModule {

    private AutoInflateViewModuleListener mAutoInflateViewModuleListener;

    @Override
    public void onModuleLoaded(AutoInflateViewModuleListener module) {
        mAutoInflateViewModuleListener = module;
    }

}
