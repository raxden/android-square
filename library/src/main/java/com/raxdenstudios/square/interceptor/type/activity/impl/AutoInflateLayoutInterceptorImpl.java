package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.AutoInflateLayoutInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.AutoInflateLayoutInterceptorConfig;

import java.util.Locale;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class AutoInflateLayoutInterceptorImpl
        extends ActivityInterceptor<AutoInflateLayoutInterceptorConfig, AutoInflateLayoutInterceptorCallback>
        implements AutoInflateLayoutInterceptorConfig {

    private View mInflateLayout;

    public AutoInflateLayoutInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflateLayout = onCreateView(mActivity.getLayoutInflater());
        if (mInflateLayout != null) {
            mActivity.setContentView(mInflateLayout);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mCallback.onContentViewCreated(mInflateLayout, savedInstanceState);
    }

    private View onCreateView(LayoutInflater inflater) {
        return inflateLayout(inflater);
    }

    private View inflateLayout(LayoutInflater inflater) {
        int layoutId = ResourceUtils.getLayoutId(mActivity, getLayoutName());
        if (layoutId > 0) {
            return inflater.inflate(layoutId, null);
        }
        return null;
    }

    private String getLayoutName() {
        return StringUtils
                .join(StringUtils
                        .uncapitalize(mActivity.getClass().getSimpleName())
                        .split("(?=\\p{Upper})"), "_")
                .toLowerCase(Locale.getDefault());
    }

}

