package com.raxdenstudios.square.interceptor.type;

import android.os.Bundle;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;
import com.raxdenstudios.square.lifecycle.PresenterLifecycle;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public abstract class PresenterInterceptor<TView extends IView, TConfig extends InterceptorConfig, TCallback extends InterceptorCallback<TConfig>>
        extends BaseInterceptor<TConfig, TCallback>
        implements PresenterLifecycle<TView> {

    protected Presenter mPresenter;

    public PresenterInterceptor(Presenter presenter) {
        super(presenter);
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
