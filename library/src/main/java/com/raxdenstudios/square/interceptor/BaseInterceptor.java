package com.raxdenstudios.square.interceptor;

import android.app.Activity;
import android.app.Application;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;

import com.raxdenstudios.mvp.presenter.Presenter;

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
public abstract class BaseInterceptor<TConfig extends InterceptorConfig, TCallback extends InterceptorCallback<TConfig>>
        implements Interceptor {

    protected Context mContext;
    protected TCallback mCallback;
    protected TConfig mConfig;

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Application application) {
        if (application instanceof InterceptorConfig) {
            mConfig = (TConfig) application;
        }
        if (application instanceof InterceptorCallback) {
            mCallback = (TCallback) application;
            mCallback.onInterceptorAttached(mConfig);
        }
        mContext = application.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Activity activity) {
        if (activity instanceof InterceptorConfig) {
            mConfig = (TConfig) activity;
        }
        if (activity instanceof InterceptorCallback) {
            mCallback = (TCallback) activity;
            mCallback.onInterceptorAttached(mConfig);
        }
        mContext = activity;
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Fragment fragment) {
        if (fragment instanceof InterceptorConfig) {
            mConfig = (TConfig) fragment;
        }
        if (fragment instanceof InterceptorCallback) {
            mCallback = (TCallback) fragment;
            mCallback.onInterceptorAttached(mConfig);
        }
        mContext = fragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(DialogFragment dialogFragment) {
        if (dialogFragment instanceof InterceptorConfig) {
            mConfig = (TConfig) dialogFragment;
        }
        if (dialogFragment instanceof InterceptorCallback) {
            mCallback = (TCallback) dialogFragment;
            mCallback.onInterceptorAttached(mConfig);
        }
        mContext = dialogFragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Presenter presenter) {
        if (presenter instanceof InterceptorConfig) {
            mConfig = (TConfig) presenter;
        }
        if (presenter instanceof InterceptorCallback) {
            mCallback = (TCallback) presenter;
            mCallback.onInterceptorAttached(mConfig);
        }
        mContext = presenter.mContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mCallback = null;
    }

}
