package com.raxdenstudios.square.fragment.interceptor;

import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 16/07/2015.
 */
public interface WebViewInterceptor extends Interceptor {

    void onConfigureWebSettings(WebSettings settings);

    ViewGroup onLoadWebViewContainer();

    void onWebViewCreate(WebView webView);

    void onProgressShow(String progressLabel);

    void onProgressHide();

}
