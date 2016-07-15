package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleExtrasInterceptor extends Interceptor {

    void onHandleExtras(Bundle savedInstanceState, Bundle intentExtras);

    interface BundleExtrasInterceptorCallback {

    }

}
