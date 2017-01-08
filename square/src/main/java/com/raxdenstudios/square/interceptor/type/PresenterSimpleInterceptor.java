package com.raxdenstudios.square.interceptor.type;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an presenter interceptor.
 */
public abstract class PresenterSimpleInterceptor<TView extends IView>
        extends PresenterInterceptor<TView, InterceptorInteractor, InterceptorCallback<InterceptorInteractor>> {

    public PresenterSimpleInterceptor(Presenter presenter) {
        super(presenter);
    }

}
