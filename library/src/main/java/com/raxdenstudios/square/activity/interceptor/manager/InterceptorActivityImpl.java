package com.raxdenstudios.square.activity.interceptor.manager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.square.activity.InterceptorActivity;

/**
 * Created by agomez on 21/04/2015.
 */
public class InterceptorActivityImpl implements IInterceptorActivity {

    private static final String TAG = InterceptorActivityImpl.class.getSimpleName();

    private Activity mActivity;

    public InterceptorActivityImpl(Activity activity) {
        if (!(activity instanceof InterceptorActivity)) {
            throw new IllegalStateException(this.getClass().getSimpleName()+" interceptor must be used just on IInterceptorActivity");
        }
        mActivity = activity;
    }

    @Override
    public void onInterceptorSaveInstanceState(Bundle outState) {

    }


    @Override
    public void onInterceptorActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onInterceptorConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void onInterceptorAttachBaseContextInterceptors(Context newBase) {

    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onInterceptorPostCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onInterceptorPrepareOptionsMenu(Menu menu) {

    }

    @Override
    public void onInterceptorResume() {

    }

    @Override
    public void onInterceptorStart() {

    }

    @Override
    public void onInterceptorStop() {

    }

    @Override
    public void onInterceptorPause() {

    }

    @Override
    public boolean onInterceptorBackPressed() {
        return false;
    }

    @Override
    public void onInterceptorDestroy() {

    }

    public Context getContext() {
        return mActivity;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public FragmentManager getFragmentManager() {
        return mActivity.getFragmentManager();
    }

    public FragmentTransaction getFragmentTransaction() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            return fragmentManager.beginTransaction();
        }
        return null;
    }

}
