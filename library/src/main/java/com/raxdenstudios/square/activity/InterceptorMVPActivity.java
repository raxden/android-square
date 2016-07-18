package com.raxdenstudios.square.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.mvp.MVPActivity;
import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.activity.interceptor.manager.IInterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityManager;

import java.util.List;

/**
 * Created by agomez on 22/05/2015.
 */
public abstract class InterceptorMVPActivity<TPresenter extends IPresenter> extends MVPActivity<TPresenter> {

    private static final String TAG = InterceptorMVPActivity.class.getSimpleName();

    /* Interceptor Manager */
    private InterceptorActivityManager mInterceptorManager;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getInterceptorManager().onSaveInstanceStateInterceptors(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreateInterceptors(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getInterceptorManager().onPostCreateInterceptors(savedInstanceState);
    }

    @Override /* Called whenever we call ActivityCompat.invalidateOptionsMenu(); */
    public boolean onPrepareOptionsMenu(Menu menu) {
        getInterceptorManager().onPrepareOptionsMenuInterceptors(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInterceptorManager().onResumeInterceptors();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getInterceptorManager().onStartInterceptors();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getInterceptorManager().onPauseInterceptors();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getInterceptorManager().onStopInterceptors();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroyInterceptors();
    }

    /* Callbacks */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInterceptorManager().onActivityResultInterceptors(requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChangedInterceptors(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getInterceptorManager().onBackPressedInterceptors()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    /* Support methods */

    private InterceptorActivityManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = new InterceptorActivityManager(this);
            addInterceptor(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addInterceptor(List<IInterceptorActivity> interceptors) {

    }

    protected List<IInterceptorActivity> getInterceptors() {
        return mInterceptorManager.getInterceptors();
    }

}
