package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;

import com.raxdenstudios.analytics.FlurryHelper;
import com.raxdenstudios.square.fragment.module.FlurryModule;
import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class FlurryModuleImpl extends ModuleFragmentImpl {

    private static final String TAG = FlurryModuleImpl.class.getSimpleName();

    public FlurryModuleImpl(ModularFragment activity) {
        if (!(activity instanceof FlurryModule)) {
            throw new IllegalStateException("Fragment must implement FlurryModule.");
        }
    }

    @Override
    public void onModuleStart(Context context) {
        super.onModuleStart(context);
        FlurryHelper.getInstance().startSession(context);
    }

    @Override
    public void onModuleStop(Context context) {
        super.onModuleStop(context);
        FlurryHelper.getInstance().endSession(context);
    }
}