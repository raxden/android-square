package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.fragment.interceptor.WebViewInterceptor;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

/**
 * Created by agomez on 16/07/2015.
 */
public class WebViewInterceptorImpl extends InterceptorFragmentImpl implements WebViewInterceptor.WebViewInterceptorCallback {

    private WebViewInterceptor mCallbacks;
    private ViewGroup mContainer;
    private WebView mWebView;

    public WebViewInterceptorImpl(Fragment fragment) {
        super(fragment);
        if (!(fragment instanceof WebViewInterceptor)) {
            throw new IllegalStateException("Fragment must implement WebViewInterceptor.");
        }
        mCallbacks = (WebViewInterceptor)fragment;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mContainer = mCallbacks != null ? mCallbacks.onLoadWebViewContainer() : null;
        if (mContainer != null) {
            mWebView = new WebView(getContext());
            mWebView.setId(R.id.app__webview);

            mContainer.removeAllViews();
            mContainer.addView(mWebView);

            mWebView.setScrollBarStyle(ScrollView.SCROLLBARS_INSIDE_OVERLAY);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.getSettings().setAllowFileAccess(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            }
            mWebView.getSettings().setBuiltInZoomControls(false);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                mWebView.getSettings().setTextZoom(80);
            }

            CookieManager.getInstance().setAcceptCookie(true);

            if (mCallbacks != null) mCallbacks.onConfigureWebSettings(mWebView.getSettings());

            mWebView.setWebViewClient(webviewClient);
            mWebView.setWebChromeClient(webChromeClient);
        }
    }

    @Override
    public void onInterceptorResume() {
        super.onInterceptorResume();
        if (mWebView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) mWebView.onResume();
    }

    @Override
    public void onInterceptorPause() {
        super.onInterceptorPause();
        if (mWebView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) mWebView.onPause();
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();

        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        if (mContainer != null) mContainer.removeAllViews();
    }

    @Override
    public WebView getWebView() {
        return mWebView;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (view != null) {
            if (mCallbacks != null) mCallbacks.onProgressShow(Integer.toString(newProgress) + "%");
            if (newProgress == 100) {
                if (mCallbacks != null) mCallbacks.onProgressShow(Integer.toString(newProgress));
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (view != null) {
            if (mCallbacks != null) mCallbacks.onProgressHide();
        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            WebViewInterceptorImpl.this.onProgressChanged(view, newProgress);
        }
    };

    private WebViewClient webviewClient = new WebViewClient() {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            WebViewInterceptorImpl.this.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

    };
}
