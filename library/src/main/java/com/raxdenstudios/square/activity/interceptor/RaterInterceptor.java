package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.RaterInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.impl.RaterInterceptorImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public interface RaterInterceptor extends Interceptor {

    void onInterceptorCreated(RaterInterceptorDelegate delegate);

    void onRaterInterceptorClick(RaterInterceptorImpl.RaterOption optionSelected);

}
