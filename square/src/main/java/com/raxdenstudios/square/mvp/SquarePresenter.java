package com.raxdenstudios.square.mvp;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.type.PresenterInterceptor;
import com.raxdenstudios.square.interceptor.manager.InterceptorManagerFactory;
import com.raxdenstudios.square.interceptor.manager.PresenterInterceptorManager;

import java.util.List;

/**
 * Created by Ángel Gómez
 *
 * SquarePresenter is an abstract class that adds interceptor functionality to the presenter.
 */
public abstract class SquarePresenter<TView extends IView> extends Presenter<TView> {

    private PresenterInterceptorManager<TView> mInterceptorManager;

    public SquarePresenter(Context context) {
        super(context);
    }

    @Override
    public void onTakeView(TView view) {
        super.onTakeView(view);
        getInterceptorManager().onTakeView(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getInterceptorManager().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getInterceptorManager().onPause();
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();
        getInterceptorManager().onViewLoaded();
    }

    @Override
    public void onSave(Bundle outState) {
        super.onSave(outState);
        getInterceptorManager().onSave(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroy();
    }

    @Override
    public void onDropView() {
        super.onDropView();
        getInterceptorManager().onDropView();
    }

    /* Support methods */

    private PresenterInterceptorManager<TView> getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (PresenterInterceptorManager) InterceptorManagerFactory.buildManager(this);
            addCustomInterceptorToStack(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addCustomInterceptorToStack(List<PresenterInterceptor> interceptors) {

    }

}
