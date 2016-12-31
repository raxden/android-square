package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;

import com.raxdenstudios.commons.util.StringUtils;
import com.raxdenstudios.square.R;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.CountBackInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.CountBackInterceptorConfig;

/**
 * Created by Ángel Gómez on 06/05/2015.
 */
public class CountBackInterceptorImpl
        extends ActivityInterceptor<CountBackInterceptorConfig, CountBackInterceptorCallback>
        implements CountBackInterceptorConfig {

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

    public CountBackInterceptorImpl(Activity activity) {
        super(activity);

        if (!StringUtils.hasText(mMessage)) {
            mMessage = mActivity.getString(R.string.app__count_back_exit_message);
        }
        if (mDefaultCountBackToExit == 0) {
            mDefaultCountBackToExit = DEFAULT_NUM_COUNT_BACK;
            mCountBackToExit = mDefaultCountBackToExit;
        }
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
