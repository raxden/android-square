package com.raxdenstudios.square.interceptor.handlearguments;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 22/05/2015.
 */
public interface HandleArgumentsInterceptorCallback
        extends InterceptorCallback<HandleArgumentsInteractor> {

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

}
