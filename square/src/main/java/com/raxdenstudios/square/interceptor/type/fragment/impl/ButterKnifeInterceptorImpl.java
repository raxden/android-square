package com.raxdenstudios.square.interceptor.type.fragment.impl;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.type.FragmentInterceptor;
import com.raxdenstudios.square.interceptor.callback.ButterKnifeInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.ButterKnifeInterceptorInteractor;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Raxden on 23/07/2016.
 */
public class ButterKnifeInterceptorImpl
        extends FragmentInterceptor<ButterKnifeInterceptorInteractor, ButterKnifeInterceptorCallback>
        implements ButterKnifeInterceptorInteractor {

    private Unbinder unbinder;

    public ButterKnifeInterceptorImpl(Fragment fragment) {
        super(fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(mFragment, inflatedView);
        return inflatedView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
