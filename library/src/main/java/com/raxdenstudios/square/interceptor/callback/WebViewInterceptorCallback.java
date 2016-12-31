package com.raxdenstudios.square.interceptor.callback;

import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.WebViewInterceptorConfig;

/**
 * Created by agomez on 16/07/2015.
 */
public interface WebViewInterceptorCallback
        extends InterceptorCallback<WebViewInterceptorConfig> {

    void onConfigureWebSettings(WebSettings settings);

    ViewGroup onLoadWebViewContainer();

    void onWebViewCreate(WebView webView);

    void onProgressShow(String progressLabel);

    void onProgressHide();

}
