package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.rater.RaterHelper;
import com.raxdenstudios.rater.RaterManager;
import com.raxdenstudios.square.activity.interceptor.RaterInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.RaterInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class RaterInterceptorImpl extends InterceptorActivityImpl<RaterInterceptor>
        implements RaterInterceptorCallback {

    private static final String TAG = RaterInterceptor.class.getSimpleName();

    private RaterManager mRaterManager;
    public enum RaterOption {RATE, REMIND_LATER, DONT_SHOW_AGAIN}

    public RaterInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mRaterManager = new RaterManager(getContext());
    }

    @Override
    public void onInterceptorPostCreate(Bundle savedInstanceState) {
        super.onInterceptorPostCreate(savedInstanceState);

        mRaterManager.showRaterDialogIfNecessary(getContext(), new RaterManager.AppRaterCallbacks() {
            @Override
            public void onDialogClickRate() {
                mCallbacks.onRaterInterceptorClick(RaterOption.RATE);
            }

            @Override
            public void onDialogClickRemindLater() {
                mCallbacks.onRaterInterceptorClick(RaterOption.REMIND_LATER);
            }

            @Override
            public void onDialogClickDontShowAgain() {
                mCallbacks.onRaterInterceptorClick(RaterOption.DONT_SHOW_AGAIN);
            }
        });
    }

    @Override
    public void showRaterDialog() {
        mRaterManager.showRaterDialog(getContext(), new RaterManager.AppRaterCallbacks() {
            @Override
            public void onDialogClickRate() {
                mCallbacks.onRaterInterceptorClick(RaterOption.RATE);
            }

            @Override
            public void onDialogClickRemindLater() {
                mCallbacks.onRaterInterceptorClick(RaterOption.REMIND_LATER);
            }

            @Override
            public void onDialogClickDontShowAgain() {
                mCallbacks.onRaterInterceptorClick(RaterOption.DONT_SHOW_AGAIN);
            }
        });
    }

    @Override
    public boolean isDontShowAgain() {
        return RaterHelper.getInstance().isDontShowAgain(getContext());
    }

    @Override
    public void setDontShowAgain(boolean dontShowAgain) {
        RaterHelper.getInstance().setDontShowAgain(getContext(), dontShowAgain);
    }

    @Override
    public long getLaunchCounter() {
        return RaterHelper.getInstance().getLaunchCounter(getContext());
    }

    @Override
    public void setLaunchCounter(long launchCount) {
        RaterHelper.getInstance().setLaunchCounter(getContext(), launchCount);
    }

    @Override
    public void setFirstLaunch(long firstLaunch) {
        RaterHelper.getInstance().setFirstLaunch(getContext(), firstLaunch);
    }

    @Override
    public long getFirstLaunch() {
        return RaterHelper.getInstance().getFirstLaunch(getContext());
    }

}
