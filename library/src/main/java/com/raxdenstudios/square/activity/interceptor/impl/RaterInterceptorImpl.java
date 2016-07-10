package com.raxdenstudios.square.activity.interceptor.impl;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.rater.RaterHelper;
import com.raxdenstudios.rater.RaterManager;
import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.RaterInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class RaterInterceptorImpl extends InterceptorActivityImpl implements RaterInterceptor.RaterInterceptorCallback {

    private static final String TAG = RaterInterceptor.class.getSimpleName();

    private RaterInterceptor mCallbacks;
    private RaterManager mRaterManager;
    public enum RaterOption {RATE, REMIND_LATER, DONT_SHOW_AGAIN}

    public RaterInterceptorImpl(InterceptorActivity activity) {
        if (!(activity instanceof RaterInterceptor)) {
            throw new IllegalStateException("Activity must implement RaterInterceptor.");
        }
        mCallbacks = (RaterInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);

        mRaterManager = new RaterManager(context);
        if (mCallbacks != null) mCallbacks.onInterceptorLoaded(this);
    }

    @Override
    public void onInterceptorPostCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorPostCreate(context, savedInstanceState);

        mRaterManager.showRaterDialogIfNecessary(context, new RaterManager.AppRaterCallbacks() {
            @Override
            public void onDialogClickRate() {
                if (mCallbacks != null) mCallbacks.onRaterInterceptorClick(RaterOption.RATE);
            }

            @Override
            public void onDialogClickRemindLater() {
                if (mCallbacks != null)
                    mCallbacks.onRaterInterceptorClick(RaterOption.REMIND_LATER);
            }

            @Override
            public void onDialogClickDontShowAgain() {
                if (mCallbacks != null)
                    mCallbacks.onRaterInterceptorClick(RaterOption.DONT_SHOW_AGAIN);
            }
        });
    }

    @Override
    public void showRaterDialog(Context context) {
        mRaterManager.showRaterDialog(context, new RaterManager.AppRaterCallbacks() {
            @Override
            public void onDialogClickRate() {
                if (mCallbacks != null) mCallbacks.onRaterInterceptorClick(RaterOption.RATE);
            }

            @Override
            public void onDialogClickRemindLater() {
                if (mCallbacks != null)
                    mCallbacks.onRaterInterceptorClick(RaterOption.REMIND_LATER);
            }

            @Override
            public void onDialogClickDontShowAgain() {
                if (mCallbacks != null)
                    mCallbacks.onRaterInterceptorClick(RaterOption.DONT_SHOW_AGAIN);
            }
        });
    }

    @Override
    public boolean isDontShowAgain(Context context) {
        return RaterHelper.getInstance().isDontShowAgain(context);
    }

    @Override
    public void setDontShowAgain(Context context, boolean dontShowAgain) {
        RaterHelper.getInstance().setDontShowAgain(context, dontShowAgain);
    }

    @Override
    public long getLaunchCounter(Context context) {
        return RaterHelper.getInstance().getLaunchCounter(context);
    }

    @Override
    public void setLaunchCounter(Context context, long launchCount) {
        RaterHelper.getInstance().setLaunchCounter(context, launchCount);
    }

    @Override
    public void setFirstLaunch(Context context, long firstLaunch) {
        RaterHelper.getInstance().setFirstLaunch(context, firstLaunch);
    }

    @Override
    public long getFirstLaunch(Context context) {
        return RaterHelper.getInstance().getFirstLaunch(context);
    }

}
