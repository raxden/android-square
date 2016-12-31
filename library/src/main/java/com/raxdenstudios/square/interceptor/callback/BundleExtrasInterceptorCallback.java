package com.raxdenstudios.square.interceptor.callback;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.BundleExtrasInterceptorConfig;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface BundleExtrasInterceptorCallback
        extends InterceptorCallback<BundleExtrasInterceptorConfig> {

    void onHandleExtras(Bundle savedInstanceState, Bundle extras);

}
