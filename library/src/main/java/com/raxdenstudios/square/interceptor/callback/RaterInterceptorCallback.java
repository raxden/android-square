package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.RaterInterceptorConfig;

/**
 * Created by agomez on 06/05/2015.
 */
public interface RaterInterceptorCallback
        extends InterceptorCallback<RaterInterceptorConfig> {

    void onRaterClickRate();

    void onRaterClickRemindLater();

    void onRaterClickDontShowAgain();

}
