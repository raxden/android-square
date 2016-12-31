package com.raxdenstudios.square.interceptor.callback;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.ToolbarInterceptorConfig;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptorCallback
        extends InterceptorCallback<ToolbarInterceptorConfig> {

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

}
