package com.raxdenstudios.square.fragment.interceptor;

import android.os.Bundle;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleArgumentsInterceptor extends Interceptor<BundleArgumentsInterceptor.BundleArgumentsInterceptorCallback> {

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

    interface BundleArgumentsInterceptorCallback extends InterceptorCallback {

    }
}
