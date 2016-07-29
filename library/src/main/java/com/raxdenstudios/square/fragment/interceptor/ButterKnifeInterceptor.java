package com.raxdenstudios.square.fragment.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.fragment.interceptor.delegate.ButterKnifeInterceptorDelegate;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface ButterKnifeInterceptor extends Interceptor {

    void onInterceptorCreated(ButterKnifeInterceptorDelegate delegate);

}
