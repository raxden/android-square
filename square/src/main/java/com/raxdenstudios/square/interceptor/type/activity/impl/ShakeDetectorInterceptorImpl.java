package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.shake.ShakeDetectorHelper;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.ShakeDetectorInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.ShakeDetectorInterceptorInteractor;

/**
 * Detects phone shaking. If > 75% of the samples taken in the past 0.5s are
 * accelerating, the device is a) shaking, or b) free falling 1.84m (h =
 * 1/2*g*t^2*3/4).
 */
public class ShakeDetectorInterceptorImpl
        extends ActivityInterceptor<ShakeDetectorInterceptorInteractor, ShakeDetectorInterceptorCallback>
        implements ShakeDetectorInterceptorInteractor {

    public ShakeDetectorInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShakeDetectorHelper.getInstance()
                .startShakeDetector(mActivity, new ShakeDetectorHelper.ShakeDetectorListener() {
            @Override
            public void shakeDetected() {
                if (mCallback != null) {
                    mCallback.shakeDetected();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ShakeDetectorHelper.getInstance().stopShakeDetector();
    }

}
