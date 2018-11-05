package com.raxdenstudios.square.interceptor.reactive;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeDisposableActivityInterceptor extends ActivityInterceptor<CompositeDisposableInterceptorCallback> implements CompositeDisposableInterceptor {

    private CompositeDisposable mCompositeDisposable;

    public CompositeDisposableActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity, null);
        mCompositeDisposable = new CompositeDisposable();
    }

    public CompositeDisposableActivityInterceptor(@NonNull FragmentActivity activity, @NonNull CompositeDisposableInterceptorCallback callback) {
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
    public void addDisposable(Disposable disposable) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void removeDisposable(Disposable disposable) {
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
            if (mCompositeDisposable != null) {
                mCompositeDisposable.remove(disposable);
            }
        }
    }

    @Override
    public void removeAllDisposables() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public boolean hasDisposables() {
        if (mCompositeDisposable != null) {
            return mCompositeDisposable.size() > 0;
        } else {
            return false;
        }
    }

}
