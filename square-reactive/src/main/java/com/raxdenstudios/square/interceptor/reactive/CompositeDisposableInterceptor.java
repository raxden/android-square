package com.raxdenstudios.square.interceptor.reactive;

import com.raxdenstudios.square.interceptor.HasInterceptor;

import io.reactivex.disposables.Disposable;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CompositeDisposableInterceptor extends HasInterceptor {

    void addDisposable(Disposable disposable);

    void removeDisposable(Disposable disposable);

    void removeAllDisposables();

    boolean hasDisposables();

}
