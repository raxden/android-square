package com.raxdenstudios.square.interceptor;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an presenter interceptor.
 */
public abstract class PresenterSimpleInterceptor<TView extends IView> extends PresenterInterceptor<TView, InterceptorCallback> {

    public PresenterSimpleInterceptor(Presenter presenter) {
        super(presenter);
    }

}
