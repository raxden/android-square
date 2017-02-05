package com.raxdenstudios.square.interceptor;

import android.os.Bundle;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.lifecycle.PresenterLifecycle;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an presenter interceptor.
 */
public abstract class PresenterInterceptor<TView extends IView, TInteractor extends Interactor, TCallback extends InterceptorCallback<TInteractor>>
        extends BaseInterceptor<TInteractor, TCallback>
        implements PresenterLifecycle<TView> {

    protected Presenter mPresenter;

    public PresenterInterceptor(Presenter presenter, TCallback callback) {
        super(presenter, callback);
        mPresenter = presenter;
    }

    @Override
    public void onTakeView(TView mView) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onSave(Bundle outState) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDropView() {

    }

}
