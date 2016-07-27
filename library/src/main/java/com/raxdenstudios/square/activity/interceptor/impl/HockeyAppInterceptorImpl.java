package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.HockeyAppInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.HockeyAppInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

/**
 * Created by Raxden on 27/07/2016.
 */
public class HockeyAppInterceptorImpl extends InterceptorActivityImpl
        implements HockeyAppInterceptorDelegate {

    private HockeyAppInterceptor mCallbacks;
    private boolean mStoreBuild;

    @Override
    public void setStoreBuild(boolean storeBuild) {
        mStoreBuild = storeBuild;
    }

    public HockeyAppInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (HockeyAppInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        checkForUpdates();
    }

    @Override
    public void onInterceptorResume() {
        super.onInterceptorResume();
        checkForCrashes();
    }

    @Override
    public void onInterceptorPause() {
        super.onInterceptorPause();
        unregisterManagers();
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();
        unregisterManagers();
    }

    private void checkForCrashes() {
        CrashManager.register(getActivity());
    }

    private void checkForUpdates() {
        if (!mStoreBuild) {
            UpdateManager.register(getActivity());
        }
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }

}
