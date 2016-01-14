package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.InflateLayoutModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 25/05/2015.
 */
public class InflateLayoutModuleImpl extends ModuleActivityImpl implements InflateLayoutModule.InflateLayoutModuleListener {

    private static final String TAG = InflateLayoutModuleImpl.class.getSimpleName();

    private InflateLayoutModule mCallbacks;
    private View mInflateLayout;

    public InflateLayoutModuleImpl(ModularActivity activity) {
        if (!(activity instanceof InflateLayoutModule)) {
            throw new IllegalStateException("Activity must implement InflateLayoutModule.");
        }
        mCallbacks = (InflateLayoutModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);
        if (mCallbacks != null) {
            mInflateLayout = onCreateView(context, ((ModularActivity)mCallbacks).getLayoutInflater(), savedInstanceState);
            if (mInflateLayout != null) {
                ((ModularActivity)mCallbacks).setContentView(mInflateLayout);
                mCallbacks.onModuleLoaded(this);
            }
        }
    }

    private View onCreateView(Context context, LayoutInflater inflater, Bundle savedInstanceState) {
        View view = null;
        if (mCallbacks != null) {
            view = mCallbacks.onCreateLayout(inflater, (ViewGroup) view, savedInstanceState);
        }
        return view;
    }

    @Override
    public View getLayout() {
        return mInflateLayout;
    }
}
