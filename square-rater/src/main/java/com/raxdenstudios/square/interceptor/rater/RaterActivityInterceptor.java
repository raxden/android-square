package com.raxdenstudios.square.interceptor.rater;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.rater.RaterHelper;
import com.raxdenstudios.rater.RaterManager;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import java.util.Calendar;

/**
 * Created by agomez on 06/05/2015.
 */
public class RaterActivityInterceptor
        extends ActivityInterceptor<RaterInteractor, RaterInterceptorCallback>
        implements RaterInteractor, RaterManager.AppRaterCallbacks {

    private RaterManager mRaterManager;

    public RaterActivityInterceptor(@NonNull Activity activity) {
        super(activity);
    }

    public RaterActivityInterceptor(@NonNull Activity activity, @NonNull RaterInterceptorCallback callback) {
        super(activity, callback);
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
