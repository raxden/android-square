package com.raxdenstudios.square.fragment.interceptor;

import android.os.Bundle;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleArgumentsInterceptor extends Interceptor {

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

    interface BundleArgumentsInterceptorCallback {

    }
}
