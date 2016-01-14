package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;

import com.raxdenstudios.analytics.FlurryHelper;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class FlurryModuleImpl extends ModuleActivityImpl {

    private static final String TAG = FlurryModuleImpl.class.getSimpleName();

    public FlurryModuleImpl(ModularActivity activity) {

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