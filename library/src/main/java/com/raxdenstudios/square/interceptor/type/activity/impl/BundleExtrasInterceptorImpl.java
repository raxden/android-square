package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.BundleExtrasInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.BundleExtrasInterceptorInteractor;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class BundleExtrasInterceptorImpl
        extends ActivityInterceptor<BundleExtrasInterceptorInteractor, BundleExtrasInterceptorCallback>
        implements BundleExtrasInterceptorInteractor {

    public BundleExtrasInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = mActivity.getIntent() != null ? mActivity.getIntent().getExtras() : null;
        mCallback.onHandleExtras(savedInstanceState, extras);
    }

}
