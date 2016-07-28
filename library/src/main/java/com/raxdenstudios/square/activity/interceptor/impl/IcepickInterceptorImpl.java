package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.IcepickInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.IcepickInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

import icepick.Icepick;

/**
 * Created by Raxden on 26/07/2016.
 */
public class IcepickInterceptorImpl extends InterceptorActivityImpl
        implements IcepickInterceptorDelegate {

    private IcepickInterceptor mCallbacks;

    public IcepickInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks = (IcepickInterceptor)activity;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onInterceptorSaveInstanceState(Bundle outState) {
        super.onInterceptorSaveInstanceState(outState);
        Icepick.saveInstanceState(getActivity(), outState);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        Icepick.restoreInstanceState(getActivity(), savedInstanceState);
    }

}
