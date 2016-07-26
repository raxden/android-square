package com.raxdenstudios.square.fragment.interceptor.delegate;

import android.webkit.WebView;

import com.raxdenstudios.square.InterceptorDelegate;

/**
 * Created by Raxden on 25/07/2016.
 */
public interface WebViewInterceptorDelegate extends InterceptorDelegate {

    void onProgressChanged(WebView view, int newProgress);

    void onPageFinished(WebView view, String url);

}
