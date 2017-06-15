package com.raxdenstudios.square;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.PresenterInterceptor;
import com.raxdenstudios.square.manager.InterceptorManagerFactory;
import com.raxdenstudios.square.manager.PresenterInterceptorManager;

import java.util.ArrayList;
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

    protected abstract void setupInterceptors(List<Interceptor> interceptorList);

    private PresenterInterceptorManager<TView> getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (PresenterInterceptorManager) InterceptorManagerFactory.buildManager(this);
            List<Interceptor> interceptorList = new ArrayList<>();
            setupInterceptors(interceptorList);
            for (Interceptor interceptor : interceptorList) {
                if (interceptor instanceof PresenterInterceptor) {
                    mInterceptorManager.addInterceptor((PresenterInterceptor) interceptor);
                }
            }
        }
        return mInterceptorManager;
    }

}
