package com.raxdenstudios.square.fragment.interceptor;

import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by agomez on 16/07/2015.
 */
public interface WebViewInterceptor {

    void onInterceptorLoaded(WebViewInterceptorCallback callback);

    void onConfigureWebSettings(WebSettings settings);

    ViewGroup onLoadWebViewContainer();

    void onProgressShow(String progressLabel);

    void onProgressHide();

    interface WebViewInterceptorCallback {
        WebView getWebView();

        void onProgressChanged(WebView view, int newProgress);

        void onPageFinished(WebView view, String url);
    }

}
