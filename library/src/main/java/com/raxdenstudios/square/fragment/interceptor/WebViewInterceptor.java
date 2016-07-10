package com.raxdenstudios.square.fragment.interceptor;

import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by agomez on 16/07/2015.
 */
public interface WebViewInterceptor extends Interceptor<WebViewInterceptor.WebViewInterceptorCallback> {

    void onConfigureWebSettings(WebSettings settings);

    ViewGroup onLoadWebViewContainer();

    void onProgressShow(String progressLabel);

    void onProgressHide();

    interface WebViewInterceptorCallback extends InterceptorCallback {
        WebView getWebView();

        void onProgressChanged(WebView view, int newProgress);

        void onPageFinished(WebView view, String url);
    }

}
