package com.raxdenstudios.square.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.mvp.MVPActivity;
import com.raxdenstudios.square.activity.interceptor.manager.IInterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityManager;

import java.util.List;

/**
 * Created by agomez on 22/05/2015.
 */
public abstract class InterceptorActivity extends MVPActivity {

    private static final String TAG = InterceptorActivity.class.getSimpleName();

    /* Interceptor Manager */
    private InterceptorActivityManager mInterceptorManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreateInterceptors(this, savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getInterceptorManager().onPostCreateInterceptors(this, savedInstanceState);
    }

    @Override /* Called whenever we call ActivityCompat.invalidateOptionsMenu(this); */
    public boolean onPrepareOptionsMenu(Menu menu) {
        getInterceptorManager().onPrepareOptionsMenuInterceptors(this, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInterceptorManager().onResumeInterceptors(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getInterceptorManager().onStartInterceptors(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getInterceptorManager().onPauseInterceptors(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getInterceptorManager().onStopInterceptors(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroyInterceptors(this);
    }

    /* Callbacks */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInterceptorManager().onActivityResultInterceptors(this, requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChangedInterceptors(this, newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getInterceptorManager().onBackPressedInterceptors(this)) {
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

}
