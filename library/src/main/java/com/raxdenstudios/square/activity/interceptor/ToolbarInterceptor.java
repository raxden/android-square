package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptor extends Interceptor {

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    interface ToolbarInterceptorCallback {
        Toolbar getToolbar();
    }

}
