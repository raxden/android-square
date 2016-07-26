package com.raxdenstudios.square.fragment.interceptor.callback;

import android.webkit.WebView;

import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by Raxden on 25/07/2016.
 */
public interface WebViewInterceptorCallback extends InterceptorCallback {

    void onProgressChanged(WebView view, int newProgress);

    void onPageFinished(WebView view, String url);

}
