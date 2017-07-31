package com.raxdenstudios.square.interceptor.commons.toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by agomez on 21/05/2015.
 */
public class ToolbarActivityInterceptorImpl extends ActivityInterceptor<ToolbarInterceptorCallback> implements ToolbarInterceptor {

    public ToolbarActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public ToolbarActivityInterceptorImpl(@NonNull Activity activity, @NonNull ToolbarInterceptorCallback callback) {
        super(activity, callback);
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
            mCallback.onToolbarViewCreated(toolbar);
        }
    }

}
