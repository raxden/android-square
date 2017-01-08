package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.raxdenstudios.square.interceptor.callback.FullScreenInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.FullScreenInterceptorInteractor;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
public class FullScreenInterceptorImpl
        extends ActivityInterceptor<FullScreenInterceptorInteractor, FullScreenInterceptorCallback>
        implements FullScreenInterceptorInteractor {

    public FullScreenInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        int mask = WindowManager.LayoutParams.FLAG_FULLSCREEN;

        mActivity.getWindow().setFlags(flags, mask);
    }

}
