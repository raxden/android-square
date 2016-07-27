package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.ButterKnifeInterceptorDelegate;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface ButterKnifeInterceptor extends Interceptor {

    void onInterceptorCreated(ButterKnifeInterceptorDelegate delegate);

}
