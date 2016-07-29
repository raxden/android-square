package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.fragment.interceptor.ButterKnifeInterceptor;
import com.raxdenstudios.square.fragment.interceptor.delegate.ButterKnifeInterceptorDelegate;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

import butterknife.ButterKnife;

/**
 * Created by Raxden on 23/07/2016.
 */
public class ButterKnifeInterceptorImpl extends InterceptorFragmentImpl
        implements ButterKnifeInterceptorDelegate {

    private ButterKnifeInterceptor mCallbacks;

    public ButterKnifeInterceptorImpl(Fragment fragment) {
        super(fragment);
        mCallbacks = (ButterKnifeInterceptor)fragment;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public View onInterceptorCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onInterceptorCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }
}
