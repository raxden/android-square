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
public abstract class BaseInterceptor<TInteractor extends Interactor, TCallback extends InterceptorCallback<TInteractor>>
        implements Interceptor {

    private static final String TAG = BaseInterceptor.class.getSimpleName();

    protected Context mContext;
    protected TCallback mCallback;
    protected TInteractor mInteractor;

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Application application, TCallback callback) {
        mCallback = callback;
        if (mCallback != null) {
            mCallback.onInterceptorAttached((TInteractor) this);
        }
        mContext = application.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Activity activity, TCallback callback) {
        mCallback = callback;
        if (mCallback != null) {
            mCallback.onInterceptorAttached((TInteractor) this);
        }
        mContext = activity;
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Fragment fragment, TCallback callback) {
        mCallback = callback;
        if (mCallback != null) {
            mCallback.onInterceptorAttached((TInteractor) this);
        }
        mContext = fragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(DialogFragment dialogFragment, TCallback callback) {
        mCallback = callback;
        if (mCallback != null) {
            mCallback.onInterceptorAttached((TInteractor) this);
        }
        mContext = dialogFragment.getActivity();
    }

    @SuppressWarnings("unchecked")
    public BaseInterceptor(Presenter presenter, TCallback callback) {
        mCallback = callback;
        if (mCallback != null) {
            mCallback.onInterceptorAttached((TInteractor) this);
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
