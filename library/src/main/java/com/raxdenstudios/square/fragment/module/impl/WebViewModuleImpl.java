package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;
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
import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.WebViewModule;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;

/**
 * Created by agomez on 16/07/2015.
 */
public class WebViewModuleImpl extends ModuleFragmentImpl implements WebViewModule.WebViewModuleListener {

    private WebViewModule mCallbacks;
    private ViewGroup mContainer;
    private WebView mWebView;

    public WebViewModuleImpl(ModularFragment fragment) {
        if (!(fragment instanceof WebViewModule)) {
            throw new IllegalStateException("Fragment must implement WebViewModule.");
        }
        mCallbacks = (WebViewModule)fragment;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        mContainer = mCallbacks != null ? mCallbacks.onLoadWebViewContainer() : null;
        if (mContainer != null) {
            mWebView = new WebView(context);
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
        if (mCallbacks != null) mCallbacks.onWebViewModuleLoaded(this);
    }

    @Override
    public void onModuleResume(Context context) {
        super.onModuleResume(context);
        if (mWebView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) mWebView.onResume();
    }

    @Override
    public void onModulePause(Context context) {
        super.onModulePause(context);
        if (mWebView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) mWebView.onPause();
    }

    @Override
    public void onModuleDestroy(Context context) {
        super.onModuleDestroy(context);

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
            WebViewModuleImpl.this.onProgressChanged(view, newProgress);
        }
    };

    private WebViewClient webviewClient = new WebViewClient() {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            WebViewModuleImpl.this.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

    };
}
