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
 * <p>
 * This abstract class defines the basis of an interceptor.
 */
public abstract class BaseInterceptor<TCallback extends InterceptorCallback> implements Interceptor {

    private static final String TAG = BaseInterceptor.class.getSimpleName();

    protected Context mContext;
    protected TCallback mCallback;

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Application application) {
        mContext = application.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Application application, TCallback callback) {
        mContext = application.getApplicationContext();
        setOnInterceptorCallback(callback);
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Activity activity) {
        mContext = activity;
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Activity activity, TCallback callback) {
        mContext = activity;
        setOnInterceptorCallback(callback);
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Fragment fragment) {
        mContext = fragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Fragment fragment, TCallback callback) {
        mContext = fragment.getActivity();
        setOnInterceptorCallback(callback);
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(DialogFragment dialogFragment) {
        mContext = dialogFragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(DialogFragment dialogFragment, TCallback callback) {
        mContext = dialogFragment.getActivity();
        setOnInterceptorCallback(callback);
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Presenter presenter) {
        mContext = presenter.mContext;
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Presenter presenter, TCallback callback) {
        mContext = presenter.mContext;
        setOnInterceptorCallback(callback);
    }

    public void setOnInterceptorCallback(TCallback callback) {
        mCallback = callback;
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
