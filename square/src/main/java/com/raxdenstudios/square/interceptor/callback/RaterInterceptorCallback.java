package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.RaterInterceptorInteractor;

/**
 * Created by agomez on 06/05/2015.
 */
public interface RaterInterceptorCallback
        extends InterceptorCallback<RaterInterceptorInteractor> {

    void onRaterClickRate();

    void onRaterClickRemindLater();

    void onRaterClickDontShowAgain();

}
