package com.raxdenstudios.square.interceptor.bundleextras;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class BundleExtrasActivityInterceptor
        extends ActivityInterceptor<BundleExtrasInteractor, BundleExtrasInterceptorCallback>
        implements BundleExtrasInteractor {

    public BundleExtrasActivityInterceptor(Activity activity, BundleExtrasInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = mActivity.getIntent() != null ? mActivity.getIntent().getExtras() : null;
        mCallback.onHandleExtras(savedInstanceState, extras);
    }

}
