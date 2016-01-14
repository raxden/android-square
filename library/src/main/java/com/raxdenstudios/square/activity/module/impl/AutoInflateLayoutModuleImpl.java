package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.AutoInflateLayoutModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;
import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;

import java.util.Locale;

/**
 * Created by agomez on 22/05/2015.
 */
public class AutoInflateLayoutModuleImpl extends ModuleActivityImpl implements AutoInflateLayoutModule.AutoInflateLayoutModuleListener {

    private static final String TAG = AutoInflateLayoutModuleImpl.class.getSimpleName();

    private AutoInflateLayoutModule mCallbacks;
    private View mInflateLayout;

    public AutoInflateLayoutModuleImpl(ModularActivity activity) {
        if (!(activity instanceof AutoInflateLayoutModule)) {
            throw new IllegalStateException("Activity must implement AutoInflateLayoutModule.");
        }
        mCallbacks = (AutoInflateLayoutModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        if (mCallbacks != null && mCallbacks instanceof ModularActivity) {
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
            view = inflateLayout(context, inflater);
        }
        return view;
    }

    private View inflateLayout(Context context, LayoutInflater inflater) {
        String layoutToSearch = StringUtils.join(StringUtils.uncapitalize(context.getClass().getSimpleName()).split("(?=\\p{Upper})"), "_").toLowerCase(Locale.getDefault());
        int layoutId = ResourceUtils.getLayoutId(context, layoutToSearch);
        if (layoutId > 0) {
            return inflater.inflate(layoutId, null);
        }
        return null;
    }

    @Override
    public View getLayout() {
        return mInflateLayout;
    }
}

