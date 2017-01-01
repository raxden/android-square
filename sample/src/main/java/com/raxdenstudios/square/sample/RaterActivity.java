package com.raxdenstudios.square.sample;

import android.os.Bundle;
import android.util.Log;

import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.interactor.RaterInterceptorInteractor;
import com.raxdenstudios.square.interceptor.type.activity.RaterActivityInterceptor;

public class RaterActivity extends SquareActivity implements RaterActivityInterceptor {

    private static final String TAG = RaterActivity.class.getSimpleName();

    RaterInterceptorInteractor mRaterInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRaterInteractor.showRaterDialog();
    }

    @Override
    public void onInterceptorAttached(RaterInterceptorInteractor interactor) {
        mRaterInteractor = interactor;
    }

    @Override
    public void onRaterClickRate() {
        Log.d(TAG, "onRaterClickRate");
    }

    @Override
    public void onRaterClickRemindLater() {
        Log.d(TAG, "onRaterClickRemindLater");
    }

    @Override
    public void onRaterClickDontShowAgain() {
        Log.d(TAG, "onRaterClickDontShowAgain");
    }

}
