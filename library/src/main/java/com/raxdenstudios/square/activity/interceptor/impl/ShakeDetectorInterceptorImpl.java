package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.shake.ShakeDetectorHelper;
import com.raxdenstudios.square.activity.interceptor.ShakeDetectorInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.ShakeDetectorInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Detects phone shaking. If > 75% of the samples taken in the past 0.5s are
 * accelerating, the device is a) shaking, or b) free falling 1.84m (h =
 * 1/2*g*t^2*3/4).
 */
public class ShakeDetectorInterceptorImpl extends InterceptorActivityImpl
        implements ShakeDetectorInterceptorDelegate {

    private ShakeDetectorInterceptor mCallbacks;

    public ShakeDetectorInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks = (ShakeDetectorInterceptor)activity;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        ShakeDetectorHelper.getInstance().startShakeDetector(getContext(), new ShakeDetectorHelper.ShakeDetectorListener() {
            @Override
            public void shakeDetected() {
                if (mCallbacks != null) mCallbacks.shakeDetected();
            }
        });
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();

        ShakeDetectorHelper.getInstance().stopShakeDetector();
    }

}
