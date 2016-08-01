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
import butterknife.Unbinder;

/**
 * Created by Raxden on 23/07/2016.
 */
public class ButterKnifeInterceptorImpl extends InterceptorFragmentImpl
        implements ButterKnifeInterceptorDelegate {

    private ButterKnifeInterceptor mCallbacks;
    private Unbinder unbinder;

    public ButterKnifeInterceptorImpl(Fragment fragment) {
        super(fragment);
        mCallbacks = (ButterKnifeInterceptor)fragment;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public View onInterceptorCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(getFragment(), inflatedView);
        return inflatedView;
    }

    @Override
    public void onInterceptorDestroyView() {
        super.onInterceptorDestroyView();
        unbinder.unbind();
    }
}
