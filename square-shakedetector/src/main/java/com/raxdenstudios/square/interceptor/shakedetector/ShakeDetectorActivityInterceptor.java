package com.raxdenstudios.square.interceptor.shakedetector;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.shake.ShakeDetectorHelper;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Detects phone shaking. If > 75% of the samples taken in the past 0.5s are
 * accelerating, the device is a) shaking, or b) free falling 1.84m (h =
 * 1/2*g*t^2*3/4).
 */
public class ShakeDetectorActivityInterceptor
        extends ActivityInterceptor<ShakeDetectorInteractor, ShakeDetectorInterceptorCallback>
        implements ShakeDetectorInteractor {

    public ShakeDetectorActivityInterceptor(@NonNull Activity activity) {
        super(activity);
    }

    public ShakeDetectorActivityInterceptor(@NonNull Activity activity, @NonNull ShakeDetectorInterceptorCallback callback) {
        super(activity, callback);
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
