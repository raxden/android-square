package com.raxdenstudios.square.interceptor;

import android.app.Activity;
import android.app.Application;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.raxdenstudios.mvp.presenter.Presenter;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an interceptor.
 */
public abstract class BaseInterceptor<TInteractor extends InterceptorInteractor, TCallback extends InterceptorCallback<TInteractor>>
        implements Interceptor {

    private static final String TAG = BaseInterceptor.class.getSimpleName();

    protected Context mContext;
    protected TCallback mCallback;
    protected TInteractor mInteractor;

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Application application) {
        if (application instanceof InterceptorInteractor) {
            mInteractor = (TInteractor) application;
        }
        if (application instanceof InterceptorCallback) {
            mCallback = (TCallback) application;
            mCallback.onInterceptorAttached(mInteractor);
        }
        mContext = application.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Activity activity) {
        if (activity instanceof InterceptorInteractor) {
            mInteractor = (TInteractor) activity;
        }
        if (activity instanceof InterceptorCallback) {
            mCallback = (TCallback) activity;
            mCallback.onInterceptorAttached(mInteractor);
        }
        mContext = activity;
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Fragment fragment) {
        if (fragment instanceof InterceptorInteractor) {
            mInteractor = (TInteractor) fragment;
        }
        if (fragment instanceof InterceptorCallback) {
            mCallback = (TCallback) fragment;
            mCallback.onInterceptorAttached(mInteractor);
        }
        mContext = fragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(DialogFragment dialogFragment) {
        if (dialogFragment instanceof InterceptorInteractor) {
            mInteractor = (TInteractor) dialogFragment;
        }
        if (dialogFragment instanceof InterceptorCallback) {
            mCallback = (TCallback) dialogFragment;
            mCallback.onInterceptorAttached(mInteractor);
        }
        mContext = dialogFragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Presenter presenter) {
        if (presenter instanceof InterceptorInteractor) {
            mInteractor = (TInteractor) presenter;
        }
        if (presenter instanceof InterceptorCallback) {
            mCallback = (TCallback) presenter;
            mCallback.onInterceptorAttached(mInteractor);
        }
        mContext = presenter.mContext;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, this.getClass().getSimpleName() + " created!");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, this.getClass().getSimpleName() + " destroyed!");
        mCallback = null;
    }

}
