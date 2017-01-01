package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.IcepickInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.IcepickInterceptorInteractor;

import icepick.Icepick;

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
public class IcepickInterceptorImpl
        extends ActivityInterceptor<IcepickInterceptorInteractor, IcepickInterceptorCallback>
        implements IcepickInterceptorInteractor {

    public IcepickInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(mActivity, outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(mActivity, savedInstanceState);
    }

}
