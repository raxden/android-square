package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.rater.RaterHelper;
import com.raxdenstudios.rater.RaterManager;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.RaterInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.RaterInterceptorInteractor;

import java.util.Calendar;

/**
 * Created by agomez on 06/05/2015.
 */
public class RaterInterceptorImpl
        extends ActivityInterceptor<RaterInterceptorInteractor, RaterInterceptorCallback>
        implements RaterInterceptorInteractor, RaterManager.AppRaterCallbacks {

    private RaterManager mRaterManager;

    public RaterInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRaterManager = new RaterManager(mActivity);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mRaterManager.showRaterDialogIfNecessary(mActivity, this);
    }

    @Override
    public void reset() {
        RaterHelper.getInstance().setFirstLaunch(mContext, Calendar.getInstance().getTimeInMillis());
        RaterHelper.getInstance().setLaunchCounter(mContext, 0);
    }

    @Override
    public void showRaterDialog() {
        mRaterManager.showRaterDialog(mActivity, this);
    }

    @Override
    public void showRaterDialogIfNecessary() {
        mRaterManager.showRaterDialogIfNecessary(mActivity, this);
    }

    @Override
    public void onDialogClickRate() {
        mCallback.onRaterClickRate();
    }

    @Override
    public void onDialogClickRemindLater() {
        mCallback.onRaterClickRemindLater();
    }

    @Override
    public void onDialogClickDontShowAgain() {
        mCallback.onRaterClickDontShowAgain();
    }
}
