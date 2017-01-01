package com.raxdenstudios.square.interceptor.callback;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.AutoInflateLayoutInterceptorInteractor;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface AutoInflateLayoutInterceptorCallback
        extends InterceptorCallback<AutoInflateLayoutInterceptorInteractor> {

    void onContentViewCreated(View view, Bundle savedInstanceState);

}
