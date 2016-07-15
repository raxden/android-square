package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.shake.ShakeDetectorHelper;
import com.raxdenstudios.square.activity.interceptor.ShakeDetectorInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Detects phone shaking. If > 75% of the samples taken in the past 0.5s are
 * accelerating, the device is a) shaking, or b) free falling 1.84m (h =
 * 1/2*g*t^2*3/4).
 */
public class ShakeDetectorInterceptorImpl extends InterceptorActivityImpl implements ShakeDetectorInterceptor.ShakeDetectorInterceptorCallback {

    private ShakeDetectorInterceptor mCallbacks;

    public ShakeDetectorInterceptorImpl(Activity activity) {
        if (!(activity instanceof ShakeDetectorInterceptor)) {
            throw new IllegalStateException("Activity must implement ShakeDetectorInterceptor.");
        }
        mCallbacks = (ShakeDetectorInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);

        ShakeDetectorHelper.getInstance().startShakeDetector(context, new ShakeDetectorHelper.ShakeDetectorListener() {
            @Override
            public void shakeDetected() {
                if (mCallbacks != null) mCallbacks.shakeDetected();
            }
        });
    }

    @Override
    public void onInterceptorDestroy(Context context) {
        super.onInterceptorDestroy(context);

        ShakeDetectorHelper.getInstance().stopShakeDetector();
    }

}
