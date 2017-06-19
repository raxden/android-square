package com.raxdenstudios.square;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.mvp.MVPActivity;
import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.manager.ActivityInterceptorManager;
import com.raxdenstudios.square.manager.InterceptorManagerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ángel Gómez
 *
 * SquareMVPActivity is an abstract class that adds interceptor functionality to the activity.
 * Unlike SquareActivity this activity follows the MVP pattern, therefore has a presenter attached.
 */
public abstract class SquareMVPActivity<TPresenter extends IPresenter>
        extends MVPActivity<TPresenter> {

    private ActivityInterceptorManager mInterceptorManager;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getInterceptorManager().onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getInterceptorManager().onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getInterceptorManager().onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override /* Called whenever we call ActivityCompat.invalidateOptionsMenu(); */
    public boolean onPrepareOptionsMenu(Menu menu) {
        getInterceptorManager().onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInterceptorManager().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getInterceptorManager().onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getInterceptorManager().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getInterceptorManager().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroy();
    }

    /* Callbacks */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInterceptorManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getInterceptorManager().onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    /* Support methods */

    protected abstract void setupInterceptors(List<Interceptor> interceptorList);

    private ActivityInterceptorManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (ActivityInterceptorManager) InterceptorManagerFactory.buildManager(this);
            List<Interceptor> interceptorList = new ArrayList<>();
            setupInterceptors(interceptorList);
            for (Interceptor interceptor : interceptorList) {
                if (interceptor instanceof ActivityInterceptor) {
                    mInterceptorManager.addInterceptor((ActivityInterceptor) interceptor);
                }
            }
        }
        return mInterceptorManager;
    }

}
