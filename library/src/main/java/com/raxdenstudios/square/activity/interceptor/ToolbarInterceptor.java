package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptor {

    void onInterceptorLoaded(ToolbarInterceptorCallback callback);

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    interface ToolbarInterceptorCallback {
        Toolbar getToolbar();
    }

}
