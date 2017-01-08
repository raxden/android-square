package com.raxdenstudios.square.interceptor.manager;

import android.os.Bundle;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.type.PresenterInterceptor;
import com.raxdenstudios.square.lifecycle.PresenterLifecycle;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public class PresenterInterceptorManager<TView extends IView>
        extends InterceptorManager<Presenter, PresenterInterceptor>
        implements PresenterLifecycle<TView> {

    public PresenterInterceptorManager(Presenter presenter) {
        super(presenter);
    }

    @Override
    public void onTakeView(TView mView) {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onTakeView(mView);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onResume();
        }
    }

    @Override
    public void onPause() {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onPause();
        }
    }

    @Override
    public void onViewLoaded() {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onViewLoaded();
        }
    }

    @Override
    public void onSave(Bundle outState) {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onSave(outState);
        }
    }

    @Override
    public void onDestroy() {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onDestroy();
        }
    }

    @Override
    public void onDropView() {
        for (PresenterInterceptor interceptor : interceptors) {
            interceptor.onDropView();
        }
    }

}
