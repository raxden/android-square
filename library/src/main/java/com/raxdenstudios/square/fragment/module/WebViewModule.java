package com.raxdenstudios.square.fragment.module;

import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by agomez on 16/07/2015.
 */
public interface WebViewModule {

    void onWebViewModuleLoaded(WebViewModuleListener module);
    void onConfigureWebSettings(WebSettings settings);
    ViewGroup onLoadWebViewContainer();

    void onProgressShow(String progressLabel);
    void onProgressHide();

    interface WebViewModuleListener {
        WebView getWebView();
        void onProgressChanged(WebView view, int newProgress);
        void onPageFinished(WebView view, String url);
    }
}
