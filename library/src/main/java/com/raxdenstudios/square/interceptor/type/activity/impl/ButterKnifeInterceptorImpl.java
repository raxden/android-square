package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.ButterKnifeInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.ButterKnifeInterceptorInteractor;

import butterknife.ButterKnife;

/**
 * Created by Ángel Gómez on 23/07/2016.
 */
public class ButterKnifeInterceptorImpl
        extends ActivityInterceptor<ButterKnifeInterceptorInteractor, ButterKnifeInterceptorCallback>
        implements ButterKnifeInterceptorInteractor {

    public ButterKnifeInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
    }

}