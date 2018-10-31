package com.raxdenstudios.square.interceptor.commons.toolbar;

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
public class ToolbarActivityInterceptor extends ActivityInterceptor<ToolbarInterceptorCallback> implements ToolbarInterceptor {

    public ToolbarActivityInterceptor(@NonNull AppCompatActivity activity) {
        super(activity);
    }

    public ToolbarActivityInterceptor(@NonNull AppCompatActivity activity, @NonNull ToolbarInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = getCallback().onCreateToolbarView(savedInstanceState);
        if (toolbar != null) {
            if (getActivity() instanceof AppCompatActivity) {
                AppCompatActivity compatActivity = ((AppCompatActivity) getActivity());
                compatActivity.setSupportActionBar(toolbar);
                ActionBar actionBar = compatActivity.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }
            }
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return getActivity().onOptionsItemSelected(item);
                }
            });
            getCallback().onToolbarViewCreated(toolbar);
        }
    }

}
