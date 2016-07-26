package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.raxdenstudios.square.activity.interceptor.ToolbarInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.ToolbarInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 21/05/2015.
 */
public class ToolbarInterceptorImpl extends InterceptorActivityImpl<ToolbarInterceptor>
        implements ToolbarInterceptorCallback {

    private static final String TAG = ToolbarInterceptorImpl.class.getSimpleName();

    private Toolbar mToolbar;

    public ToolbarInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mToolbar = mCallbacks.onCreateToolbarView(savedInstanceState);
        if (mToolbar != null) {
            AppCompatActivity compatActivity = ((AppCompatActivity)mActivity);
            compatActivity.setSupportActionBar(mToolbar);
            ActionBar actionBar = compatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
            }
            mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return mActivity.onOptionsItemSelected(item);
                }
            });
            mCallbacks.onToolbarViewCreated(mToolbar, savedInstanceState);
        }
    }

}
