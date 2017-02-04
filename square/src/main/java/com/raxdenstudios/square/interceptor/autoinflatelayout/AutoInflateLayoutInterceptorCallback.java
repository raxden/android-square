package com.raxdenstudios.square.interceptor.autoinflatelayout;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface AutoInflateLayoutInterceptorCallback
        extends InterceptorCallback<AutoInflateLayoutInteractor> {

    void onContentViewCreated(View view, Bundle savedInstanceState);

}
