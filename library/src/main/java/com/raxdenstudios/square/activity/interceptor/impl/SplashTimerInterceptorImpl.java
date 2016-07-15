package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.raxdenstudios.square.activity.interceptor.SplashTimerInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 11/05/2105.
 */
public class SplashTimerInterceptorImpl extends InterceptorActivityImpl implements SplashTimerInterceptor.SplashTimerInterceptorCallback {

    private static final String TAG = SplashTimerInterceptorImpl.class.getSimpleName();

    private final static int DEFAULT_TIME_MS = 3000;

    private SplashTimerInterceptor mCallbacks;
    private SplashTimerConfiguration mConfig;
    private Handler mHandler = new Handler();
    private boolean isSplashEnded;
    private Object o = new Object();

    private Runnable splashRunnableTimer = new Runnable() {
        @Override
        public void run() {
            synchronized (o) {
                isSplashEnded = true;
                if (mCallbacks != null) {
                    mCallbacks.onSplashTimerEnd();
                }
            }
            removeRunnable();
        }
    };

    public static class SplashTimerConfiguration {
        public long time;
    }

    public static class DefaultSplashTimerConfiguration extends SplashTimerConfiguration {
        public DefaultSplashTimerConfiguration() {
            this.time = DEFAULT_TIME_MS;
        }
    }

    public SplashTimerInterceptorImpl(Activity activity) {
        if (!(activity instanceof SplashTimerInterceptor)) {
            throw new IllegalStateException("Activity must implement SplashTimerInterceptor.");
        }
        mCallbacks = (SplashTimerInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);

        if (mConfig == null) {
            mConfig = new DefaultSplashTimerConfiguration();
        }

        removeRunnable();
        postRunnable();
    }

    @Override
    public boolean onInterceptorBackPressed(Context context) {
        removeRunnable();
        if (mCallbacks != null) mCallbacks.onSplashTimerCancelled();
        return super.onInterceptorBackPressed(context);
    }

    @Override
    public void onInterceptorDestroy(Context context) {
        super.onInterceptorDestroy(context);

        synchronized (o) {
            if (!isSplashEnded) {
                if (mCallbacks != null) mCallbacks.onSplashTimerCancelled();
            }
            removeRunnable();
        }
    }

    private void removeRunnable() {
        if (mHandler != null) mHandler.removeCallbacks(splashRunnableTimer);
    }

    private void postRunnable() {
        if (mHandler != null) mHandler.postDelayed(splashRunnableTimer, mConfig.time);
    }

    @Override
    public SplashTimerConfiguration getConfiguration() {
        return mConfig;
    }

}
