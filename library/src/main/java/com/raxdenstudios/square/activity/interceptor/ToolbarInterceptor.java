package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptor {

    Toolbar onCreateToolbarView(Bundle savedInstanceState);
    void onInterceptorLoaded(ToolbarInterceptorListener interceptor);

    interface ToolbarInterceptorListener {
        Toolbar getToolbar();
    }

}
