package com.raxdenstudios.square.interceptor.bundlearguments;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleArgumentsInterceptorCallback
        extends InterceptorCallback<BundleArgumentsInteractor> {

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

}
