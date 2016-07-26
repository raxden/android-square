package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.ToolbarInterceptorDelegate;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptor extends Interceptor {

    void onInterceptorCreated(ToolbarInterceptorDelegate callback);

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    void onToolbarViewCreated(Toolbar toolbar, Bundle savedInstanceState);

}
