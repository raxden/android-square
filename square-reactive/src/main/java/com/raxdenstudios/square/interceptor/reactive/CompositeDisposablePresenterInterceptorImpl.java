package com.raxdenstudios.square.interceptor.reactive;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.PresenterInterceptor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeDisposablePresenterInterceptorImpl<TView extends IView> extends PresenterInterceptor<TView, CompositeDisposableInterceptorCallback> implements CompositeDisposableInterceptor {

    private CompositeDisposable mCompositeDisposable;

    public CompositeDisposablePresenterInterceptorImpl(Presenter<TView> presenter) {
        super(presenter);
        mCompositeDisposable = new CompositeDisposable();
    }

    public CompositeDisposablePresenterInterceptorImpl(Presenter<TView> presenter, CompositeDisposableInterceptorCallback callback) {
        super(presenter, callback);
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
