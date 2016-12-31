package com.raxdenstudios.square.interceptor.type;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public abstract class PresenterSimpleInterceptor<TView extends IView>
        extends PresenterInterceptor<TView, InterceptorConfig, InterceptorCallback<InterceptorConfig>> {

    public PresenterSimpleInterceptor(Presenter presenter) {
        super(presenter);
    }

}
