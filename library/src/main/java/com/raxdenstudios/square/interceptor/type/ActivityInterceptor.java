package com.raxdenstudios.square.interceptor.type;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorInteractor;
import com.raxdenstudios.square.lifecycle.ActivityLifecycle;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an activity interceptor.
 */
public abstract class ActivityInterceptor<TInteractor extends InterceptorInteractor, TCallback extends InterceptorCallback<TInteractor>>
        extends BaseInterceptor<TInteractor, TCallback>
        implements ActivityLifecycle {

    protected Activity mActivity;

    public ActivityInterceptor(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void attachBaseContext(Context newBase) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu) {

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }

}
