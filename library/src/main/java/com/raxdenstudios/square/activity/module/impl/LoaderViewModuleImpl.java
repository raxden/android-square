package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.LoaderViewModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 02/06/2015.
 */
public class LoaderViewModuleImpl extends ModuleActivityImpl implements LoaderViewModule.LoaderViewModuleListener {

    private View mLoaderView;
    private Object o = new Object();
    private LoaderViewModule mCallbacks;

    public LoaderViewModuleImpl(ModularActivity activity) {
        if (!(activity instanceof LoaderViewModule)) {
            throw new IllegalStateException("Activity must implement LoaderViewModule.");
        }
        mCallbacks = (LoaderViewModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        if (mCallbacks != null) {
            mLoaderView = mCallbacks.onCreateLoaderView(savedInstanceState);
            mCallbacks.onModuleLoaded(this);
        }
    }

    @Override
    public void onProgressShow(Context context, String progressLabel) {
        super.onProgressShow(context, progressLabel);

        show(context, R.id.app__progress_label, progressLabel);
    }

    @Override
    public void onProgressHide(Context context) {
        super.onProgressHide(context);

        hide(context);
    }

    @Override
    public void show(Context context) {
        synchronized (o) {
            show(context, null);
        }
    }

    @Override
    public void show(Context context, String loaderLabel) {
        synchronized (o) {
            show(context, R.id.app__progress_label, loaderLabel);
        }
    }

    @Override
    public void show(Context context, int loaderLabelId, String loaderLabel) {
        synchronized (o) {
            if (mLoaderView != null) {
                TextView progressLabelView = ((TextView) mLoaderView.findViewById(loaderLabelId));
                if (progressLabelView != null) {
                    progressLabelView.setText(loaderLabel);
                }
                if (mLoaderView.getVisibility() == View.GONE) {
                    mLoaderView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_fade_in));
                    mLoaderView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void hide(Context context) {
        synchronized (o) {
            if (mLoaderView != null && mLoaderView.getVisibility() == View.VISIBLE) {
                mLoaderView.setVisibility(View.GONE);
                mLoaderView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_fade_out));
            }
        }
    }
}
