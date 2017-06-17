package com.raxdenstudios.square.interceptor.reactive;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeDisposableActivityInterceptorImpl extends ActivityInterceptor<CompositeDisposableInterceptorCallback> implements CompositeDisposableInterceptor {

    private CompositeDisposable mCompositeDisposable;

    public CompositeDisposableActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
        mCompositeDisposable = new CompositeDisposable();
    }

    public CompositeDisposableActivityInterceptorImpl(@NonNull Activity activity, @NonNull CompositeDisposableInterceptorCallback callback) {
        super(activity, callback);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void addSubscriber(ResourceSubscriber subscriber) {
        if (subscriber != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(ResourceSubscriber subscriber) {
        if (subscriber != null) {
            if (!subscriber.isDisposed()) {
                subscriber.dispose();
            }
            if (mCompositeDisposable != null) {
                mCompositeDisposable.remove(subscriber);
            }
        }
    }

    @Override
    public void removeAllSubscribers() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public boolean hasSubscribers() {
        if (mCompositeDisposable != null) {
            return mCompositeDisposable.size() > 0;
        } else {
            return false;
        }
    }

}
