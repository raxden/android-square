package com.raxdenstudios.square.fragment.interceptor;

import android.os.Bundle;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleArgumentsInterceptor {

    void onInterceptorLoaded(BundleArgumentsInterceptorCallback callback);

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

    interface BundleArgumentsInterceptorCallback {

    }
}
