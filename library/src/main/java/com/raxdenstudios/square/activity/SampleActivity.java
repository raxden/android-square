package com.raxdenstudios.square.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.activity.interceptor.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.ToolbarInterceptor;

/**
 * Created by Raxden on 10/07/2016.
 */
public class SampleActivity extends InterceptorActivity implements AutoInflateLayoutInterceptor, ToolbarInterceptor {
    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return null;
    }

    @Override
    public IPresenter initializePresenter(Context context) {
        return null;
    }

    @Override
    public void onInterceptorLoaded(AutoInflateLayoutInterceptorCallback autoInflateLayoutInterceptorCallback) {

    }

    @Override
    public void onInterceptorLoaded(ToolbarInterceptorCallback toolbarInterceptorCallback) {

    }
}
