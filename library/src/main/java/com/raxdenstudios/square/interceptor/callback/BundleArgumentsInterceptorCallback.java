package com.raxdenstudios.square.interceptor.callback;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.BundleArgumentsInterceptorConfig;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleArgumentsInterceptorCallback
        extends InterceptorCallback<BundleArgumentsInterceptorConfig> {

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

}
