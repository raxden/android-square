package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.raxdenstudios.square.activity.interceptor.InflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.InflateLayoutInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 25/05/2015.
 */
public class InflateLayoutInterceptorImpl extends InterceptorActivityImpl
        implements InflateLayoutInterceptorCallback {

    private static final String TAG = InflateLayoutInterceptorImpl.class.getSimpleName();

    private InflateLayoutInterceptor mCallbacks;
    private View mInflateLayout;

    public InflateLayoutInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (InflateLayoutInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mInflateLayout = onCreateView(getActivity().getLayoutInflater(), savedInstanceState);
        if (mInflateLayout != null) {
            getActivity().setContentView(mInflateLayout);
            mCallbacks.onViewCreated(mInflateLayout, savedInstanceState);
        }
    }

    private View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        return mCallbacks.onCreateView(inflater, savedInstanceState);
    }

}
