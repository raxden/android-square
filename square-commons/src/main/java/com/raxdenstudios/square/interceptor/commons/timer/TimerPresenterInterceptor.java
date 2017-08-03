package com.raxdenstudios.square.interceptor.commons.timer;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.PresenterInterceptor;

/**
 * Created by agomez on 11/05/2105.
 */
public class TimerPresenterInterceptor<TView extends IView> extends PresenterInterceptor<TView, TimerInterceptorCallback> implements TimerInterceptor {

    private final static int DEFAULT_TIME_MS = 3000;

    private Handler mHandler = new Handler();
    private long time;
    private boolean isTimerEnded;
    private Object o = new Object();

    private Runnable runnableTimer = new Runnable() {
        @Override
        public void run() {
            synchronized (o) {
                isTimerEnded = true;
                if (mCallback != null) {
                    mCallback.onTimerEnd();
                }
            }
            removeRunnableIfExists();
        }
    };

    public TimerPresenterInterceptor(@NonNull Presenter presenter) {
        super(presenter);
        init();
    }

    public TimerPresenterInterceptor(@NonNull Presenter presenter, @NonNull TimerInterceptorCallback callback) {
        super(presenter, callback);
        init();
    }

    private void init() {
        if (time == 0) {
            time = DEFAULT_TIME_MS;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        removeRunnableIfExists();
        postRunnable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        synchronized (o) {
            if (!isTimerEnded) {
                if (mCallback != null) mCallback.onTimerCancelled();
            }
            removeRunnableIfExists();
        }
    }

    @Override
    public void setTime(int seconds) {
        time = seconds * 1000;
    }

    @Override
    public void setTime(long milliseconds) {
        time = milliseconds;
    }

    private void removeRunnableIfExists() {
        if (mHandler != null) mHandler.removeCallbacks(runnableTimer);
    }

    private void postRunnable() {
        if (mHandler != null) mHandler.postDelayed(runnableTimer, time);
    }

}
