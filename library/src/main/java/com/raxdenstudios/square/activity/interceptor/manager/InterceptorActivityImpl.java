package com.raxdenstudios.square.activity.interceptor.manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.square.InterceptorCallback;
import com.raxdenstudios.square.activity.InterceptorActivity;

/**
 * Created by agomez on 21/04/2015.
 */
public class InterceptorActivityImpl implements IInterceptorActivity, InterceptorCallback {

    private static final String TAG = InterceptorActivityImpl.class.getSimpleName();

    @Override
    public void onInterceptorActivityResult(Context context, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onInterceptorConfigurationChanged(Context context, Configuration configuration) {

    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        checkContextIfModularActivityInstance(context);
    }

    @Override
    public void onInterceptorPostCreate(Context context, Bundle savedInstanceState) {

    }

    @Override
    public void onInterceptorPrepareOptionsMenu(Context context, Menu menu) {

    }

    @Override
    public void onInterceptorResume(Context context) {

    }

    @Override
    public void onInterceptorStart(Context context) {

    }

    @Override
    public void onInterceptorStop(Context context) {

    }

    @Override
    public void onInterceptorPause(Context context) {

    }

    @Override
    public boolean onInterceptorBackPressed(Context context) {
        return false;
    }

    @Override
    public void onInterceptorDestroy(Context context) {

    }

    protected void checkContextIfModularActivityInstance(Context context) {
        if (!(context instanceof InterceptorActivity)) {
            throw new IllegalStateException(this.getClass().getSimpleName()+" interceptor must be used just on IInterceptorActivity");
        }
    }
}
