package com.raxdenstudios.square.interceptor.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import butterknife.ButterKnife;

/**
 * Created by Ángel Gómez on 23/07/2016.
 */
public class ButterKnifeActivityInterceptorImpl extends ActivityInterceptor<ButterKnifeInterceptorCallback> implements ButterKnifeInterceptor {

    public ButterKnifeActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public ButterKnifeActivityInterceptorImpl(@NonNull Activity activity, @NonNull ButterKnifeInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
    }

}
