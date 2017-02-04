package com.raxdenstudios.square.interceptor.rater;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 06/05/2015.
 */
public interface RaterInterceptorCallback
        extends InterceptorCallback<RaterInteractor> {

    void onRaterClickRate();

    void onRaterClickRemindLater();

    void onRaterClickDontShowAgain();

}
