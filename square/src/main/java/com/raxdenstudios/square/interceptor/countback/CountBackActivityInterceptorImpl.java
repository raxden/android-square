package com.raxdenstudios.square.interceptor.countback;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 06/05/2015.
 */
public class CountBackActivityInterceptorImpl extends ActivityInterceptor<CountBackInterceptorCallback> implements CountBackInterceptor {

    private static final int DEFAULT_NUM_COUNT_BACK = 1;

    /* Variables to handle the exit */
    private Toast exitToast;
    private int mDefaultCountBackToExit;
    private int mCountBackToExit;
    private String mMessage;

    private Handler mHandler = new Handler();
    private Runnable restartCountBackToExit = new Runnable() {
        public void run() {
            mCountBackToExit = mDefaultCountBackToExit;
        }
    };

    public CountBackActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
        init();
    }

    public CountBackActivityInterceptorImpl(@NonNull Activity activity, @NonNull CountBackInterceptorCallback callback) {
        super(activity, callback);
        init();
    }

    private void init() {
        if (mMessage != null && mMessage.length() > 0) {
            mMessage = mActivity.getString(R.string.app__count_back_exit_message);
        }
        if (mDefaultCountBackToExit == 0) {
            mDefaultCountBackToExit = DEFAULT_NUM_COUNT_BACK;
        }
        mCountBackToExit = mDefaultCountBackToExit;
    }

    @Override
    public boolean onBackPressed() {
        if (mActivity.getFragmentManager().getBackStackEntryCount() > 0) {
            return false;
        }
        if (exitToast == null) {
            exitToast = Toast.makeText(mActivity, mMessage, Toast.LENGTH_SHORT);
        }
        if (mCountBackToExit > 0) {
            mCountBackToExit--;
            removeCallbacks();
            mHandler.postDelayed(restartCountBackToExit, 2000);
            exitToast.show();
            return true;
        } else {
            exitToast.cancel();
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeCallbacks();
        destroyToast();
    }

    @Override
    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public void setRetries(int retries) {
        mDefaultCountBackToExit = retries;
    }

    private void removeCallbacks() {
        if (mHandler != null) {
            mHandler.removeCallbacks(restartCountBackToExit);
        }
    }

    private void destroyToast() {
        if (exitToast != null) {
            exitToast.cancel();
            exitToast = null;
        }
    }

}
