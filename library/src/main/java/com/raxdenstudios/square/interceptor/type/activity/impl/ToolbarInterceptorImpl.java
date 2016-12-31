package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.ToolbarInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.ToolbarInterceptorConfig;

/**
 * Created by agomez on 21/05/2015.
 */
public class ToolbarInterceptorImpl
        extends ActivityInterceptor<ToolbarInterceptorConfig, ToolbarInterceptorCallback>
        implements ToolbarInterceptorConfig {

    public ToolbarInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = mCallback.onCreateToolbarView(savedInstanceState);
        if (toolbar != null) {
            if (mActivity instanceof AppCompatActivity) {
                AppCompatActivity compatActivity = ((AppCompatActivity) mActivity);
                compatActivity.setSupportActionBar(toolbar);
                ActionBar actionBar = compatActivity.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }
            }
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return mActivity.onOptionsItemSelected(item);
                }
            });
        }
    }

}
