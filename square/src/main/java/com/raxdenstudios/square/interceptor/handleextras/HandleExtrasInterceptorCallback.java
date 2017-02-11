package com.raxdenstudios.square.interceptor.handleextras;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface HandleExtrasInterceptorCallback
        extends InterceptorCallback<HandleExtrasInteractor> {

    void onHandleExtras(Bundle savedInstanceState, Bundle extras);

}
