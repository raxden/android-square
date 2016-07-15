package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.ToolbarInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 21/05/2015.
 */
public class ToolbarInterceptorImpl extends InterceptorActivityImpl implements ToolbarInterceptor.ToolbarInterceptorCallback {

    private static final String TAG = ToolbarInterceptorImpl.class.getSimpleName();

    protected Toolbar mToolbar;

    private ToolbarInterceptor mCallbacks;

    public ToolbarInterceptorImpl(Activity activity) {
        if (!(activity instanceof ToolbarInterceptor)) {
            throw new IllegalStateException("Activity must implement ToolbarInterceptor.");
        }
        mCallbacks = (ToolbarInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(final Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);

        if (mToolbar == null) {
            if (mCallbacks != null) mToolbar = mCallbacks.onCreateToolbarView(savedInstanceState);
            if (mToolbar != null) {
                ((InterceptorActivity)context).setSupportActionBar(mToolbar);
                ActionBar actionBar = ((InterceptorActivity)context).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }
                mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return ((InterceptorActivity)context).onOptionsItemSelected(item);
                    }
                });
            }
        }
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

}
