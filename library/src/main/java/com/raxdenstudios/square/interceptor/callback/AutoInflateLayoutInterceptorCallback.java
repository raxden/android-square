package com.raxdenstudios.square.interceptor.callback;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.AutoInflateLayoutInterceptorConfig;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface AutoInflateLayoutInterceptorCallback
        extends InterceptorCallback<AutoInflateLayoutInterceptorConfig> {

    void onContentViewCreated(View view, Bundle savedInstanceState);

}
