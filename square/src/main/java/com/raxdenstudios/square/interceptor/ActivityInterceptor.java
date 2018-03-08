package com.raxdenstudios.square.interceptor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.square.lifecycle.ActivityLifecycle;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an activity interceptor.
 */
public abstract class ActivityInterceptor<TCallback extends InterceptorCallback> extends BaseInterceptor<TCallback> implements ActivityLifecycle {

    protected Activity mActivity;

    public ActivityInterceptor(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    public ActivityInterceptor(Activity activity, TCallback callback) {
        super(activity, callback);
        mActivity = activity;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onNewIntent(Intent intent) {

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
