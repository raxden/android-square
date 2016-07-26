package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.raxdenstudios.square.activity.interceptor.TimerInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.TimerInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 11/05/2105.
 */
public class TimerInterceptorImpl extends InterceptorActivityImpl
        implements TimerInterceptorDelegate {

    private static final String TAG = TimerInterceptorImpl.class.getSimpleName();

    private final static int DEFAULT_TIME_MS = 3000;

    private TimerInterceptor mCallbacks;
    private Handler mHandler = new Handler();
    private long defaultTime;
    private long time;
    private boolean isTimerEnded;
    private Object o = new Object();

    private Runnable runnableTimer = new Runnable() {
        @Override
        public void run() {
            synchronized (o) {
                isTimerEnded = true;
                if (mCallbacks != null) {
                    mCallbacks.onTimerEnd();
                }
            }
            removeRunnableIfExists();
        }
    };

    public TimerInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (TimerInterceptor)activity;

        if (defaultTime == 0) {
            defaultTime = DEFAULT_TIME_MS;
            time = defaultTime;
        }
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        removeRunnableIfExists();
        postRunnable();
    }

    @Override
    public boolean onInterceptorBackPressed() {
        removeRunnableIfExists();
        if (mCallbacks != null) mCallbacks.onTimerCancelled();
        return super.onInterceptorBackPressed();
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();

        synchronized (o) {
            if (!isTimerEnded) {
                if (mCallbacks != null) mCallbacks.onTimerCancelled();
            }
            removeRunnableIfExists();
        }
    }

    @Override
    public void setTimer(int seconds) {
        defaultTime = seconds * 1000;
    }

    @Override
    public void setTimer(long miliseconds) {
        defaultTime = miliseconds;
    }

    private void removeRunnableIfExists() {
        if (mHandler != null) mHandler.removeCallbacks(runnableTimer);
    }

    private void postRunnable() {
        if (mHandler != null) mHandler.postDelayed(runnableTimer, time);
    }

}
