package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.CountBackInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class CountBackInterceptorImpl extends InterceptorActivityImpl implements CountBackInterceptor.CountBackInterceptorCallback {

    private static final String TAG = CountBackInterceptor.class.getSimpleName();

    /* Variables to handle the exit */
    protected Toast exitToast;
    protected int countBackToExit = 1;

    private CountBackConfiguration mConfig;

    private CountBackInterceptor mCallbacks;

    /* Handler */
    protected Handler mHandler = new Handler();

    /* Runnable */
    protected Runnable restartCountBackToExit = new Runnable() {
        public void run() {
            countBackToExit = 1;
        }
    };

    public static class CountBackConfiguration {
        public String exitMessage;
        public CountBackConfiguration() {}
    }

    public static class DefaultCountBackConfiguration extends CountBackConfiguration {
        public DefaultCountBackConfiguration(Context context) {
            this.exitMessage = context.getString(R.string.app__count_back_exit_message);
        }
    }

    public CountBackInterceptorImpl(Activity activity) {
        super(activity);
        if (!(activity instanceof CountBackInterceptor)) {
            throw new IllegalStateException("Activity must implement CountBackInterceptor.");
        }
        mCallbacks = (CountBackInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        mConfig = new DefaultCountBackConfiguration(getContext());
    }

    @Override
    public boolean onInterceptorBackPressed() {
        if (mActivity instanceof InterceptorActivity) {
            if (mActivity.getFragmentManager().getBackStackEntryCount() > 0) {
                return false;
            }
        }

        if (exitToast == null) {
            exitToast = Toast.makeText(getContext(), mConfig.exitMessage, Toast.LENGTH_SHORT);
        }

        if (countBackToExit > 0) {
            countBackToExit--;
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
