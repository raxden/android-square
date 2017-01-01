package com.raxdenstudios.square.interceptor.interactor;

import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface RaterInterceptorInteractor extends InterceptorInteractor {

    void showRaterDialog();

    void showRaterDialogIfNecessary();

    void reset();

}
