package com.raxdenstudios.square.interceptor.commons.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;

import com.raxdenstudios.square.interceptor.commons.R;
import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor;

/**
 * Created by agomez on 16/07/2015.
 */
public class WebViewFragmentDialogInterceptor extends DialogFragmentInterceptor<WebViewInterceptorCallback> implements WebViewInterceptor {

    private ViewGroup mContainer;
    private WebView mWebView;
    private boolean mPageFinished;

    public WebViewFragmentDialogInterceptor(@NonNull DialogFragment fragment) {
        super(fragment, null);
    }

    public WebViewFragmentDialogInterceptor(@NonNull DialogFragment fragment, @NonNull WebViewInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContainer = getCallback() != null ? getCallback().onLoadWebViewContainer() : null;
        if (mContainer != null) {
            mWebView = new WebView(getContext());
            mWebView.setId(R.id.square__webview);

            mContainer.removeAllViews();
            mContainer.addView(mWebView);

            mWebView.setScrollBarStyle(ScrollView.SCROLLBARS_INSIDE_OVERLAY);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.getSettings().setAllowFileAccess(true);
            mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            mWebView.getSettings().setBuiltInZoomControls(false);
            mWebView.getSettings().setTextZoom(80);

            CookieManager.getInstance().setAcceptCookie(true);

            getCallback().onConfigureWebSettings(mWebView.getSettings());

            mWebView.setWebViewClient(webviewClient);
            mWebView.setWebChromeClient(webChromeClient);

            getCallback().onWebViewCreate(mWebView);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) mWebView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        if (mContainer != null) mContainer.removeAllViews();
    }

    private void onProgressChanged(WebView view, int newProgress) {
        if (view != null && !mPageFinished) {
            if (getCallback() != null) getCallback().onProgressShow(Integer.toString(newProgress) + "%");
        }
    }

    private void onPageFinished(WebView view, String url) {
        if (view != null) {
            if (getCallback() != null) getCallback().onProgressHide();
        }
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            WebViewFragmentDialogInterceptor.this.onProgressChanged(view, newProgress);
        }
    };

    private WebViewClient webviewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mPageFinished = false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mPageFinished = true;
            WebViewFragmentDialogInterceptor.this.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

    };
}