package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.os.Handler;

import com.raxdenstudios.square.fragment.interceptor.TimerInterceptor;
import com.raxdenstudios.square.fragment.interceptor.delegate.TimerInterceptorDelegate;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

/**
 * Created by agomez on 11/05/2105.
 */
public class TimerInterceptorImpl extends InterceptorFragmentImpl
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

    public TimerInterceptorImpl(Fragment fragment) {
        super(fragment);
        mCallbacks = (TimerInterceptor)fragment;
        mCallbacks.onInterceptorCreated(this);

        if (defaultTime == 0) {
            defaultTime = DEFAULT_TIME_MS;
            time = defaultTime;
        }
    }

    @Override
    public void onInterceptorResume() {
        super.onInterceptorResume();

        removeRunnableIfExists();
        postRunnable();
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
