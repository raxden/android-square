package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.ShakeDetectorModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;
import com.raxdenstudios.shake.ShakeDetectorHelper;

/**
 * Detects phone shaking. If > 75% of the samples taken in the past 0.5s are
 * accelerating, the device is a) shaking, or b) free falling 1.84m (h =
 * 1/2*g*t^2*3/4).
 */
public class ShakeDetectorModuleImpl extends ModuleActivityImpl {

    private ShakeDetectorModule mCallbacks;

    public ShakeDetectorModuleImpl(ModularActivity activity) {
        if (!(activity instanceof ShakeDetectorModule)) {
            throw new IllegalStateException("Activity must implement ShakeDetectorModule.");
        }
        mCallbacks = (ShakeDetectorModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        ShakeDetectorHelper.getInstance().startShakeDetector(context, new ShakeDetectorHelper.ShakeDetectorListener() {
            @Override
            public void shakeDetected() {
                if (mCallbacks != null) mCallbacks.shakeDetected();
            }
        });
    }

    @Override
    public void onModuleDestroy(Context context) {
        super.onModuleDestroy(context);

        ShakeDetectorHelper.getInstance().stopShakeDetector();
    }

}
