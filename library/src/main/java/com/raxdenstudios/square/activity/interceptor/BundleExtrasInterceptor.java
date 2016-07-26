package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.callback.BundleExtrasInterceptorCallback;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleExtrasInterceptor extends Interceptor<BundleExtrasInterceptorCallback> {

    void onHandleExtras(Bundle savedInstanceState, Bundle extras);

}
