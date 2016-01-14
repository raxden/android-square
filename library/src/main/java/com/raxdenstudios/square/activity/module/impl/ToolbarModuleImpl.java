package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.ToolbarModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 21/05/2015.
 */
public class ToolbarModuleImpl extends ModuleActivityImpl implements ToolbarModule.ToolbarModuleListener {

    private static final String TAG = ToolbarModuleImpl.class.getSimpleName();

    protected Toolbar mToolbar;

    private ToolbarModule mCallbacks;

    public ToolbarModuleImpl(ModularActivity activity) {
        if (!(activity instanceof ToolbarModule)) {
            throw new IllegalStateException("Activity must implement ToolbarModule.");
        }
        mCallbacks = (ToolbarModule)activity;
    }

    @Override
    public void onModuleCreate(final Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        if (mToolbar == null) {
            if (mCallbacks != null) mToolbar = mCallbacks.onCreateToolbarView(savedInstanceState);
            if (mToolbar != null) {
                ((ModularActivity)context).setSupportActionBar(mToolbar);
                ((ModularActivity)context).getSupportActionBar().setDisplayShowTitleEnabled(false);
                mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return ((ModularActivity)context).onOptionsItemSelected(menuItem);
                    }
                });
                if (mCallbacks != null) mCallbacks.onModuleLoaded(this);
            }
        }
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

}
