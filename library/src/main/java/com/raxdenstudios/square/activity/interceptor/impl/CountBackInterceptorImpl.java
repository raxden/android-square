package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;

import com.raxdenstudios.commons.util.StringUtils;
import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.interceptor.CountBackInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.CountBackInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class CountBackInterceptorImpl extends InterceptorActivityImpl<CountBackInterceptor>
        implements CountBackInterceptorCallback {

    private static final String TAG = CountBackInterceptor.class.getSimpleName();

    private static final int DEFAULT_NUM_COUNT_BACK = 1;

    /* Variables to handle the exit */
    private Toast exitToast;
    private int mDefaultCountBackToExit;
    private int mCountBackToExit;
    private String mMessage;

    /* Handler */
    protected Handler mHandler = new Handler();

    /* Runnable */
    protected Runnable restartCountBackToExit = new Runnable() {
        public void run() {
            mCountBackToExit = mDefaultCountBackToExit;
        }
    };

    public CountBackInterceptorImpl(Activity activity) {
        super(activity);

        if (!StringUtils.hasText(mMessage)) {
            mMessage = getContext().getString(R.string.app__count_back_exit_message);
        }
        if (mDefaultCountBackToExit == 0) {
            mDefaultCountBackToExit = DEFAULT_NUM_COUNT_BACK;
            mCountBackToExit = mDefaultCountBackToExit;
        }
    }

    @Override
    public boolean onInterceptorBackPressed() {
        if (mActivity.getFragmentManager().getBackStackEntryCount() > 0) {
            return false;
        }
        if (exitToast == null) {
            exitToast = Toast.makeText(getContext(), mMessage, Toast.LENGTH_SHORT);
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
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();

        removeCallbacks();
        destroyToast();
    }

    @Override
    public void setMessageBackToExit(String message) {
        mMessage = message;
    }

    @Override
    public void setCountBackToExit(int countBackToExit) {
        mDefaultCountBackToExit = countBackToExit;
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
