package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.CheckPlayServicesModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class CheckPlayServicesModuleImpl extends ModuleActivityImpl {

    private static final String TAG = CheckPlayServicesModuleImpl.class.getSimpleName();

    private CheckPlayServicesModule mCallbacks;

    public CheckPlayServicesModuleImpl(ModularActivity activity) {
        if (!(activity instanceof CheckPlayServicesModule)) {
            throw new IllegalStateException("Activity must implement CheckPlayServicesModule.");
        }
        mCallbacks = (CheckPlayServicesModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle bundle) {
        super.onModuleCreate(context, bundle);
        if (!Utils.checkPlayServices(context)) {
            if (mCallbacks != null) mCallbacks.onGooglePlayServicesNotSupported();
        }
    }

    @Override
    public void onModuleResume(Context context) {
        super.onModuleResume(context);
        if (!Utils.checkPlayServices(context)) {
            if (mCallbacks != null) mCallbacks.onGooglePlayServicesNotSupported();
        }
    }
}
