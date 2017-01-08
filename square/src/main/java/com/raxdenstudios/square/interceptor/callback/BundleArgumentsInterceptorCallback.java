package com.raxdenstudios.square.interceptor.callback;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.BundleArgumentsInterceptorInteractor;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleArgumentsInterceptorCallback
        extends InterceptorCallback<BundleArgumentsInterceptorInteractor> {

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

}
