package com.raxdenstudios.square.interceptor.commons.toolbar;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

import androidx.appcompat.widget.Toolbar;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptorCallback extends InterceptorCallback {

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    void onToolbarViewCreated(Toolbar toolbar);

}
