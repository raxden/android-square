package com.raxdenstudios.square.interceptor.commons.webview;

import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 16/07/2015.
 */
public interface WebViewInterceptorCallback extends InterceptorCallback {

    void onConfigureWebSettings(WebSettings settings);

    ViewGroup onLoadWebViewContainer();

    void onWebViewCreate(WebView webView);

    void onProgressShow(String progressLabel);

    void onProgressHide();

}
