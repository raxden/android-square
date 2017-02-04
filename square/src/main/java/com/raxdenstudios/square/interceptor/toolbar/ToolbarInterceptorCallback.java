package com.raxdenstudios.square.interceptor.toolbar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptorCallback
        extends InterceptorCallback<ToolbarInteractor> {

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

}
