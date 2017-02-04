package com.raxdenstudios.square.interceptor.bundleextras;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface BundleExtrasInterceptorCallback
        extends InterceptorCallback<BundleExtrasInteractor> {

    void onHandleExtras(Bundle savedInstanceState, Bundle extras);

}
